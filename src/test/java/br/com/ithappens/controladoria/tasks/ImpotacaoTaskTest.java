package br.com.ithappens.controladoria.tasks;

import br.com.ithappens.controladoria.service.importacao.ProcessoNfceService;
import br.com.ithappens.controladoria.service.importacao.ProcessoNfeService;
import br.com.ithappens.lib.task.model.Arguments;
import br.com.ithappens.lib.task.service.task.ITask;
import br.com.ithappens.lib.task.service.task.TaskRunner;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.mockito.Mockito.mock;

public class ImpotacaoTaskTest {

    private ITask importacaoTask;
    private TaskRunner runner;
    private ProcessoNfceService processoNfceService;
    private ProcessoNfeService processoNfeService;

    @Before
    public void before(){
        processoNfceService = mock( ProcessoNfceService.class );;
        processoNfeService = mock( ProcessoNfeService.class );;

        importacaoTask = new ImportacaoTask(processoNfceService, processoNfeService);
    }

    @Test
    public void testImportacao(){

        Arguments args = new Arguments();
        args.getParams().put("filial", "7");
        args.getParams().put("datamovimento", "2020-04-02");

        importacaoTask.run(args);

        Mockito.verify(processoNfceService, Mockito.times(1)).startImportacao(ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(LocalDate.class));

        Mockito.verify(processoNfeService, Mockito.times(1)).startImportacao(ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(LocalDate.class));
    }


}
