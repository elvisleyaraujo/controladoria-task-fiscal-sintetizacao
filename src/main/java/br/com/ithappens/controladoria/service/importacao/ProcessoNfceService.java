package br.com.ithappens.controladoria.service.importacao;

import br.com.ithappens.controladoria.mapper.postgresql.LoteSinteticoSerieMapper;
import br.com.ithappens.controladoria.mapper.sqlserver.ProcessoNfceMapper;
import br.com.ithappens.controladoria.model.Filial;
import br.com.ithappens.controladoria.model.LoteSinteticoSerie;
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

    private static final int PDV=1;
    private static final int FISCAL=2;
    private static final int ESTOQUE=3;
    private static final int FINANCEIRO=4;

    @Autowired
    private ProcessoNfceMapper processoNfceMapper;
    @Autowired
    private LoteSinteticoSerieMapper loteSinteticoSerieMapper;

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

        List<LoteSinteticoSerie> loteLst = recuperar(ESTOQUE, filial, dataMovimento);

        if (!loteLst.isEmpty()) {
            RETURN = loteSinteticoSerieMapper.insertLoteSinteticoSerie(loteLst);
        }

        log.info("EMPRESA: {} FILIAL: {} DATA: {} PROCESSO NFCE: MODULO ESTOQUE-ATUALIZADO: {}", filial.getEmpresa().getCodigo(),
                filial.getCodigo(),
                dataMovimento,
                loteLst.size());

        return RETURN;
    }

    //@Async
    public boolean importacaoFiscal(Filial filial, LocalDate dataMovimento){
        boolean RETURN = false;

        List<LoteSinteticoSerie> loteLst = recuperar(FISCAL, filial, dataMovimento);

        if (!loteLst.isEmpty()) {
            RETURN = loteSinteticoSerieMapper.insertLoteSinteticoSerie(loteLst);
        }

        log.info("EMPRESA: {} FILIAL: {} DATA: {} PROCESSO NFCE: MODULO FISCAL-ATUALIZADO: {}", filial.getEmpresa().getCodigo(),
                filial.getCodigo(),
                dataMovimento,
                loteLst.size());

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

    private List<LoteSinteticoSerie> recuperar(int modulo, Filial filial, LocalDate dataMovimento){
        List<LoteSinteticoSerie> loteLst = new ArrayList<LoteSinteticoSerie>();

        if (modulo==ESTOQUE){
            loteLst = processoNfceMapper.recuperarEstoque(filial.getCodigo(), dataMovimento);
        } else if (modulo==FISCAL){
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
