package br.com.ithappens.controladoria.service;

import br.com.ithappens.controladoria.mapper.LoteIntegracaoItemMapper;
import br.com.ithappens.controladoria.mapper.ProcessoNfceMapper;
import br.com.ithappens.controladoria.model.Empresa;
import br.com.ithappens.controladoria.model.LoteIntegracaoItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessoNfceService {

    @Autowired
    ProcessoNfceMapper processoNfceMapper;
    @Autowired
    LoteIntegracaoItemMapper loteIntegracaoItemMapper;

    public boolean importaNfceFiscal(){
        log.info("RECUPERANDO PROCESSO:NFCE MODULO:FISCAL");

        List<LoteIntegracaoItem> lote = null;

        try {
            lote = processoNfceMapper.recuperarFiscal(7L, LocalDate.now());

            lote.forEach(p -> {
                Empresa empresa = new Empresa();
                empresa.setId(p.getFilial().getId());
                p.setEmpresa(empresa);
                p.setId(UUID.randomUUID());
            }); }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        return loteIntegracaoItemMapper.insertLoteIntegracaoItem(lote);
    };

    public boolean importaNfceFinanceiro(){
        log.info("IMPORTAÇÃO FINANCEIRO A SER IMPLEMENTADO");
        return false;
    };

    public boolean importaNfceEstoque(){
        log.info("IMPORTAÇÃO ESTOQUE A SER IMPLEMENTADO");
        return false;
    };

    public boolean importaNfcePDV(){
        log.info("IMPORTAÇÃO PDV A SER IMPLEMENTADO");
        return false;
    };
}
