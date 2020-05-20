package br.com.ithappens.controladoria.service;

import br.com.ithappens.controladoria.mapper.postgresql.FilialMapper;
import br.com.ithappens.controladoria.model.Filial;
import br.com.ithappens.controladoria.model.LoteIntegracaoItem;
import br.com.ithappens.controladoria.service.BaseImportacao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
public class ProcessoNfceService extends BaseImportacao {

    @Autowired
    private FilialMapper filialMapper;

    public void importAndSave(String codigoEmpresa, String codigoFilial, LocalDate dataMovimento){
        List<Filial> filialList = filialMapper.recuperarFilial(codigoEmpresa, codigoFilial);
        super.importar(filialList, dataMovimento);
    }

    public CompletableFuture<Boolean> findAndSave(Filial filial, LocalDate dataMovimento){

        importacaoEstoque(filial, dataMovimento);
        importacaoFiscal(filial, dataMovimento);
        //importacaoFinanceiro(filial, dataMovimento);
        //importacaoPDV(filial, dataMovimento);

        return CompletableFuture.completedFuture(true);
    };

    @Async
    public CompletableFuture<Boolean> importacaoEstoque(Filial filial, LocalDate dataMovimento){
        CompletableFuture<Boolean> RETURN = CompletableFuture.completedFuture(false);

        List<LoteIntegracaoItem> loteLst = processoNfceMapper.recuperarEstoque(filial.getCodigo(), dataMovimento);
        loteLst.forEach(loteItem -> {
            loteItem.setEmpresa(filial.getEmpresa());
            loteItem.setFilial(filial);
            loteItem.setId(UUID.randomUUID());
        });

        if (saveInBatch(loteLst)) RETURN = CompletableFuture.completedFuture(true);

        log.info("FILIAL " + filial.getCodigo() + " PROCESSO NFCE: MODULO ESTOQUE-ATUALIZADO: " + loteLst.size());

        return RETURN;
    }

    @Async
    public CompletableFuture<Boolean> importacaoFiscal(Filial filial, LocalDate dataMovimento){
        CompletableFuture<Boolean> RETURN = CompletableFuture.completedFuture(false);

        List<LoteIntegracaoItem> loteLst = processoNfceMapper.recuperarFiscal(filial.getCodigo(), dataMovimento);
        loteLst.forEach(loteItem -> {
            loteItem.setEmpresa(filial.getEmpresa());
            loteItem.setFilial(filial);
            loteItem.setId(UUID.randomUUID());
        });

        if (saveInBatch(loteLst)) RETURN = CompletableFuture.completedFuture(true);

        log.info("FILIAL " + filial.getCodigo() + " PROCESSO NFCE: MODULO FISCAL-ATUALIZADO: " + loteLst.size());

        return RETURN;
    }

    @Async
    public CompletableFuture<Boolean> importacaoFinanceiro(Filial filial, LocalDate dataMovimento){
        CompletableFuture<Boolean> RETURN = CompletableFuture.completedFuture(false);
        return RETURN;
    }

    @Async
    public CompletableFuture<Boolean> importacaoPDV(Filial filial, LocalDate dataMovimento){
        CompletableFuture<Boolean> RETURN = CompletableFuture.completedFuture(false);
        return RETURN;
    }

    public boolean saveInBatch(List<LoteIntegracaoItem> loteLst){
        try {
            if (!loteLst.isEmpty()) {
                List<LoteIntegracaoItem> tmpLote = new ArrayList<LoteIntegracaoItem>();

                int qtdMax=999;
                int qtdIns=000;
                for (int x=00; x<loteLst.size(); x++){
                    tmpLote.add(loteLst.get(x));
                    qtdIns++;

                    if ( (qtdIns>=qtdMax) || (x==loteLst.size()-1) ) {
                        loteIntegracaoItemMapper.insertLoteIntegracaoItem(tmpLote);
                        tmpLote.clear();
                        qtdIns=00;
                    }
                }
            }
        } catch (Exception e){
            log.error("IMPORTAÇÃO NFCE MODULO FISCAL- ERRO AO PROCESSAR ");
            log.info(e.getMessage());
            return false;
        }

        return true;
    }


}
