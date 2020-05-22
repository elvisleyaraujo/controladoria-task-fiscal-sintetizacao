package br.com.ithappens.controladoria.service.validacao;

import br.com.ithappens.controladoria.mapper.postgresql.ConfiguracaoIntegradorMapper;
import br.com.ithappens.controladoria.mapper.postgresql.FilialMapper;
import br.com.ithappens.controladoria.mapper.postgresql.IntegracaoFiscalMapper;
import br.com.ithappens.controladoria.mapper.postgresql.ValidacaoFiscalMapper;
import br.com.ithappens.controladoria.model.*;
import br.com.ithappens.controladoria.model.enums.StatusValidacao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ValidacaoFiscalService {

    private final int PROCESSONFE  = 01;
    private final int PROCESSONFCE = 02;

    @Autowired
    private ValidacaoFiscalMapper validacaoFiscalMapper;
    @Autowired
    private IntegracaoFiscalMapper integracaoFiscalMapper;
    @Autowired
    private FilialMapper filialMapper;
    @Autowired
    private ConfiguracaoIntegradorMapper configuracaoIntegradorMapper;

    public void startValidacaoFiscal(String codigoEmpresa, String codigoFilial, LocalDate dataMovimento){
        List<Filial> listFilial = filialMapper.recuperarFilial(codigoEmpresa, codigoFilial);
        listFilial.forEach( f -> {
            validacaoProcesso(PROCESSONFCE, f, dataMovimento);
            validacaoProcesso(PROCESSONFE, f, dataMovimento);
        });
    }

    public void validacaoProcesso(int processo, Filial filial, LocalDate dataMovimento){

        List<ValidacaoFiscal> validacaoFiscalList = validacaoFiscalMapper.recuperarValidacaoFiscal(filial.getId(), dataMovimento, processo);
        validacaoFiscalList.forEach(p -> p.setStatus(StatusValidacao.CONSISTENTE));

        List<ConfiguracaoIntegrador> configuracaoIntegradorList = configuracaoIntegradorMapper.recuperarConfiguracaoIntegrador(processo);
        List<IntegracaoFiscal> integracaoFiscalList = integracaoFiscalMapper.recuperarIntegracaoFiscal(filial.getId(), dataMovimento, processo);

        Map<String, List<IntegracaoFiscal>> mapIntegracaoFiscal;
        mapIntegracaoFiscal = integracaoFiscalList.stream().collect(Collectors.groupingBy(IntegracaoFiscal::getSerie));

        log.info("VALIDAÇÃO PROCESSO {}, FILIAL {}, DATA {}, DADOS {}",
                (processo==1) ? "NFE" : "NFCE",
                filial.getCodigo(),
                dataMovimento,
                integracaoFiscalList.size());

        //Mapeamento das series de um periodo para validação
        mapIntegracaoFiscal.values().forEach( s -> {
            boolean validaModulos=true;

            List<Modulo> checkModuloList = s.stream().map(IntegracaoFiscal::getModulo).collect(Collectors.toList());
            List<Double> checkValorList = s.stream().map(IntegracaoFiscal::getValor).collect(Collectors.toList());

            for (ConfiguracaoIntegrador p : configuracaoIntegradorList) {
                if (checkModuloList.stream().filter(m -> m.getId().equals(p.getModulo().getId())).count()==0) {
                    validaModulos = false;
                    break;
                };
            }

            if ( (!validaModulos) || (checkValorList.stream().distinct().count()>1) ) {
                ValidacaoFiscal validacaoFiscal = new ValidacaoFiscal(s.get(0));
                validacaoFiscal.setStatus(StatusValidacao.INCONSISTENTE);
                validacaoFiscalList.remove(validacaoFiscal);
                validacaoFiscalList.add(validacaoFiscal);
            }
        });

        saveValidacaoFiscal(validacaoFiscalList);
    }

    public boolean saveValidacaoFiscal(List<ValidacaoFiscal> validacaoFiscalList){
        if (!validacaoFiscalList.isEmpty())
            return validacaoFiscalMapper.insertValidacaoFiscal(validacaoFiscalList);
        else
            return false;
    }

}
