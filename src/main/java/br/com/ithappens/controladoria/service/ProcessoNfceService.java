package br.com.ithappens.controladoria.service;

import br.com.ithappens.controladoria.mapper.postgresql.FilialMapper;
import br.com.ithappens.controladoria.mapper.postgresql.LoteIntegracaoItemMapper;
import br.com.ithappens.controladoria.mapper.sqlserver.ProcessoNfceMapper;
import br.com.ithappens.controladoria.model.Filial;
import br.com.ithappens.controladoria.model.LoteIntegracaoItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessoNfceService {

    @Autowired
    private ProcessoNfceMapper processoNfceMapper;
    @Autowired
    private FilialMapper filialMapper;
    @Autowired
    private LoteIntegracaoItemMapper loteIntegracaoItemMapper;

    public void importaNfceFiscal(){
        log.info("INICIO: RECUPERANDO PROCESSO:NFCE MODULO:FISCAL");
        List<Filial> filiaisList = filialMapper.recuperarFilial("2", "7");
        LocalDate dataMovimento = LocalDate.of(2020,05,02);
        List<CompletableFuture<Void>> executions = new ArrayList<>();
        filiaisList.parallelStream().forEach( filial -> { executions.add(findAndSaveFiscal(filial, dataMovimento));});
        executions.forEach(CompletableFuture::join);
        log.info("FIM: RECUPERANDO PROCESSO:NFCE MODULO:FISCAL");
    };

    @Async
    public CompletableFuture<Void> findAndSaveFiscal(Filial filial, LocalDate dataMovimento) {
        List<LoteIntegracaoItem> lote = processoNfceMapper.recuperarFiscal(filial.getCodigo(), dataMovimento);
        lote.forEach(x -> {
            x.setEmpresa(filial.getEmpresa());
            x.setFilial(filial);
            x.setId(UUID.randomUUID());
        });

        //TODO INSERÇÃO DE MUITAS INFORMAÇÕES
        if (!lote.isEmpty()) {
           loteIntegracaoItemMapper.insertLoteIntegracaoItem(lote);
        };

        return CompletableFuture.completedFuture(null);
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
