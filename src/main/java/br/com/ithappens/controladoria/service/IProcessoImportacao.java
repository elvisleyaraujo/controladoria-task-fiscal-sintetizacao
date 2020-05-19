package br.com.ithappens.controladoria.service;

import br.com.ithappens.controladoria.mapper.sqlserver.ProcessoNfceMapper;
import br.com.ithappens.controladoria.model.Filial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public interface IProcessoImportacao {

    public void importar(List<Filial> filiaisList, LocalDate dataMovimento);
    public CompletableFuture<Boolean> findAndSave(Filial filial, LocalDate dataMovimento);

}
