package br.com.ithappens.controladoria.service.nfce;

import br.com.ithappens.controladoria.model.Filial;
import br.com.ithappens.controladoria.model.LoteIntegracaoItem;
import br.com.ithappens.controladoria.service.ProcessoImportacao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class NfceImportacaoFiscal extends ProcessoImportacao {

    @Override
    @Async("executorControladoriaFiscalTaskBean")
    public CompletableFuture<Boolean> findAndSave(Filial filial, LocalDate dataMovimento) {
        List<LoteIntegracaoItem> loteLst = processoNfceMapper.recuperarFiscal(filial.getCodigo(), dataMovimento);
        loteLst.forEach(x -> {
            x.setEmpresa(filial.getEmpresa());
            x.setFilial(filial);
            x.setId(UUID.randomUUID());
        });

        if (!loteLst.isEmpty()) {
            List<LoteIntegracaoItem> tmpLote = new ArrayList<LoteIntegracaoItem>();

            int qtdMax=1000;
            int qtdIns=0;
            for (int x=00; x<loteLst.size(); x++){

                tmpLote.add(loteLst.get(x));
                qtdIns++;

                if ( (qtdIns>=qtdMax) || (x==loteLst.size()-1) ) {
                    loteIntegracaoItemMapper.insertLoteIntegracaoItem(tmpLote);
                    tmpLote.clear();
                    qtdIns=00;
                }
            }
        };
    log.info("FILIAL " + filial.getCodigo() + " PROCESSO NFCE: MODULO FISCAL-ATUALIZADO: " + loteLst.size());

        return CompletableFuture.completedFuture(true);
    }
}
