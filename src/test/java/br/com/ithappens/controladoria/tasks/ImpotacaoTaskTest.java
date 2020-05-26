package br.com.ithappens.controladoria.tasks;

import br.com.ithappens.controladoria.service.importacao.ProcessoNfceService;
import br.com.ithappens.controladoria.service.importacao.ProcessoNfeService;
import br.com.ithappens.lib.task.model.Arguments;
import br.com.ithappens.lib.task.service.task.ITask;
import br.com.ithappens.lib.task.service.task.TaskRunner;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.assertj.core.groups.Tuple;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
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
