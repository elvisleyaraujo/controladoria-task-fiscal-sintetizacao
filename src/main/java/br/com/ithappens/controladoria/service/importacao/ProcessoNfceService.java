package br.com.ithappens.controladoria.service.importacao;

import br.com.ithappens.controladoria.mapper.postgresql.FilialMapper;
import br.com.ithappens.controladoria.mapper.postgresql.IntegracaoFiscalMapper;
import br.com.ithappens.controladoria.mapper.sqlserver.ProcessoNfceMapper;
import br.com.ithappens.controladoria.model.constants.Constantes;
import br.com.ithappens.controladoria.model.Filial;
import br.com.ithappens.controladoria.model.IntegracaoFiscal;
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
public class ProcessoNfceService extends BaseImportacao {

    private final ProcessoNfceMapper processoNfceMapper;
    private final IntegracaoFiscalMapper integracaoFiscalMapper;

    public ProcessoNfceService(
            ProcessoNfceMapper processoNfceMapper,
            IntegracaoFiscalMapper integracaoFiscalMapper,
            FilialMapper filialMapper) {
        this.processoNfceMapper = processoNfceMapper;
        this.integracaoFiscalMapper = integracaoFiscalMapper;
        this.filialMapper = filialMapper;
    }

    @Async
    @Override
    public CompletableFuture<Void> findAndSave(Filial filial, LocalDate dataMovimento){
        importacaoEstoque(filial, dataMovimento);
        importacaoFiscal(filial, dataMovimento);
        importacaoFinanceiro(filial, dataMovimento);
        importacaoPDV(filial, dataMovimento);

        return CompletableFuture.completedFuture(null);
    };

    public boolean importacaoEstoque(Filial filial, LocalDate dataMovimento){
        boolean RETURN = false;

        List<IntegracaoFiscal> loteLst = recuperar(Constantes.MODULO_ESTOQUE, filial, dataMovimento);

        if (!loteLst.isEmpty()) {
            RETURN = integracaoFiscalMapper.insertIntegracaoFiscal(loteLst);
        }

        log.info("EMPRESA: {} FILIAL: {} DATA: {} PROCESSO NFCE: MODULO ESTOQUE-ATUALIZADO: {}",
                filial.getEmpresa().getCodigo(),
                filial.getCodigo(),
                dataMovimento,
                loteLst.size());

        return RETURN;
    }

    public boolean importacaoFiscal(Filial filial, LocalDate dataMovimento){
        boolean RETURN = false;

        List<IntegracaoFiscal> loteLst = recuperar(Constantes.MODULO_FISCAL, filial, dataMovimento);

        if (!loteLst.isEmpty()) {
            RETURN = integracaoFiscalMapper.insertIntegracaoFiscal(loteLst);
        }

        log.info("EMPRESA: {} FILIAL: {} DATA: {} PROCESSO NFCE: MODULO FISCAL-ATUALIZADO: {}", filial.getEmpresa().getCodigo(),
                filial.getCodigo(),
                dataMovimento,
                loteLst.size());

        return RETURN;
    }

    public boolean importacaoFinanceiro(Filial filial, LocalDate dataMovimento){
        return false;
    }

    public boolean importacaoPDV(Filial filial, LocalDate dataMovimento){
        return false;
    }

    private List<IntegracaoFiscal> recuperar(int modulo, Filial filial, LocalDate dataMovimento){
        List<IntegracaoFiscal> loteLst = new ArrayList<IntegracaoFiscal>();

        if (modulo==Constantes.MODULO_ESTOQUE){
            loteLst = processoNfceMapper.recuperarEstoque(filial.getCodigo(), dataMovimento);
        } else if (modulo==Constantes.MODULO_FISCAL){
            loteLst = processoNfceMapper.recuperarFiscal(filial.getCodigo(), dataMovimento);
        }

        loteLst.forEach(loteItem -> {
            loteItem.setEmpresa(filial.getEmpresa());
            loteItem.setFilial(filial);
            loteItem.setId(UUID.randomUUID());
        });

        return loteLst;
    }

}
