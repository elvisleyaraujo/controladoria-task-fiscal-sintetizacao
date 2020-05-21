package br.com.ithappens.controladoria.service;

import br.com.ithappens.controladoria.mapper.postgresql.LoteIntegracaoItemMapper;
import br.com.ithappens.controladoria.mapper.sqlserver.ProcessoNfeMapper;
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
public class ProcessoNfeService extends BaseImportacao {

    @Autowired
    private ProcessoNfeMapper processoNfeMapper;
    @Autowired
    private LoteIntegracaoItemMapper loteIntegracaoItemMapper;

    @Async
    @Override
    public CompletableFuture<Void> findAndSave(Filial filial, LocalDate dataMovimento){
        importacaoFiscal(filial, dataMovimento);
        importacaoEstoque(filial, dataMovimento);
        importacaoFinanceiro(filial, dataMovimento);

        return CompletableFuture.completedFuture(null);
    };

    //@Async
    public boolean importacaoFiscal(Filial filial, LocalDate dataMovimento){
        boolean RETURN = false;

        List<LoteIntegracaoItem> loteLst = processoNfeMapper.recuperarFiscal(filial.getCodigo(), dataMovimento);
        loteLst.forEach(loteItem -> {
            loteItem.setEmpresa(filial.getEmpresa());
            loteItem.setFilial(filial);
            loteItem.setId(UUID.randomUUID());
        });

        if (!loteLst.isEmpty()) {
            RETURN = loteIntegracaoItemMapper.insertLoteIntegracaoItem(loteLst);
        }

        log.info("FILIAL {} DATA: {} PROCESSO NFE: MODULO FISCAL-ATUALIZADO: {}", filial.getCodigo(), dataMovimento,loteLst.size());

        return RETURN;
    }

    //@Async
    public boolean importacaoFinanceiro(Filial filial, LocalDate dataMovimento){
        return false;
    }

    //@Async
    public boolean importacaoEstoque(Filial filial, LocalDate dataMovimento){
        return false;
    }
}
