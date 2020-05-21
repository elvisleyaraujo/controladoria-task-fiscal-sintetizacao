package br.com.ithappens.controladoria.service;

import br.com.ithappens.controladoria.mapper.postgresql.FilialMapper;
import br.com.ithappens.controladoria.mapper.postgresql.LoteIntegracaoItemMapper;
import br.com.ithappens.controladoria.mapper.sqlserver.ProcessoNfceMapper;
import br.com.ithappens.controladoria.model.Filial;
import br.com.ithappens.controladoria.model.LoteIntegracaoItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class ProcessoNfceService extends BaseImportacao {

    @Autowired
    private ProcessoNfceMapper processoNfceMapper;
    @Autowired
    private LoteIntegracaoItemMapper loteIntegracaoItemMapper;

    @Async
    @Override
    public CompletableFuture<Void> findAndSave(Filial filial, LocalDate dataMovimento){
        importacaoEstoque(filial, dataMovimento);
        importacaoFiscal(filial, dataMovimento);
        //importacaoFinanceiro(filial, dataMovimento);
        //importacaoPDV(filial, dataMovimento);

        return CompletableFuture.completedFuture(null);
    };

    //@Async
    public boolean importacaoEstoque(Filial filial, LocalDate dataMovimento){
        boolean RETURN = false;

        List<LoteIntegracaoItem> loteLst = recuperarEstoque(filial, dataMovimento);

        if (!loteLst.isEmpty()) {
            RETURN = loteIntegracaoItemMapper.insertLoteIntegracaoItem(loteLst);
        }

        log.info("FILIAL {} DATA: {} PROCESSO NFCE: MODULO ESTOQUE-ATUALIZADO: {}", filial.getCodigo(), dataMovimento,loteLst.size());

        return RETURN;
    }

    //@Async
    public boolean importacaoFiscal(Filial filial, LocalDate dataMovimento){
        boolean RETURN = false;

        List<LoteIntegracaoItem> loteLst = processoNfceMapper.recuperarFiscal(filial.getCodigo(), dataMovimento);
        loteLst.forEach(loteItem -> {
            loteItem.setEmpresa(filial.getEmpresa());
            loteItem.setFilial(filial);
            loteItem.setId(UUID.randomUUID());
        });

        if (!loteLst.isEmpty()) {
            RETURN = loteIntegracaoItemMapper.insertLoteIntegracaoItem(loteLst);
        }

        log.info("FILIAL {} DATA: {} PROCESSO NFCE: MODULO FISCAL-ATUALIZADO: {}", filial.getCodigo(), dataMovimento,loteLst.size());

        return RETURN;
    }

    //@Async
    public boolean importacaoFinanceiro(Filial filial, LocalDate dataMovimento){
        return false;
    }

    //@Async
    public boolean importacaoPDV(Filial filial, LocalDate dataMovimento){
        return false;
    }

    public List<LoteIntegracaoItem> recuperarEstoque(Filial filial, LocalDate dataMovimento){
        List<LoteIntegracaoItem> loteLst = processoNfceMapper.recuperarEstoque(filial.getCodigo(), dataMovimento);
        loteLst.forEach(loteItem -> {
            loteItem.setEmpresa(filial.getEmpresa());
            loteItem.setFilial(filial);
            loteItem.setId(UUID.randomUUID());
        });

        return loteLst;
    }

    public List<LoteIntegracaoItem> recuperarFiscal(Filial filial, LocalDate dataMovimento){
        List<LoteIntegracaoItem> loteLst = processoNfceMapper.recuperarFiscal(filial.getCodigo(), dataMovimento);
        loteLst.forEach(loteItem -> {
            loteItem.setEmpresa(filial.getEmpresa());
            loteItem.setFilial(filial);
            loteItem.setId(UUID.randomUUID());
        });

        return loteLst;
    }

}
