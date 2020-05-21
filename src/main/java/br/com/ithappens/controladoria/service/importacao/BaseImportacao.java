package br.com.ithappens.controladoria.service.importacao;

import br.com.ithappens.controladoria.mapper.postgresql.FilialMapper;
import br.com.ithappens.controladoria.model.Filial;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
public class BaseImportacao {

    @Autowired
    private FilialMapper filialMapper;

    public void startImportacao(String codigoEmpresa, String codigoFilial, LocalDate dataMovimento){
        List<Filial> filialList = filialMapper.recuperarFilial(codigoEmpresa, codigoFilial);
        importar(filialList, dataMovimento);
    }

    public void importar(List<Filial> filiaisList, LocalDate dataMovimento) {
        List<CompletableFuture<Void>> executions = new ArrayList<>();
        filiaisList.parallelStream().forEach( filial -> { executions.add(findAndSave(filial, dataMovimento));});
        executions.forEach(CompletableFuture::join);
    };

    public CompletableFuture<Void> findAndSave(Filial filial, LocalDate dataMovimento){
        return CompletableFuture.completedFuture(null);
    };

}
