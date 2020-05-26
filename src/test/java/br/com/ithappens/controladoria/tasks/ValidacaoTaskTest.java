package br.com.ithappens.controladoria.tasks;

import br.com.ithappens.controladoria.service.importacao.ProcessoNfceService;
import br.com.ithappens.controladoria.service.importacao.ProcessoNfeService;
import br.com.ithappens.controladoria.service.validacao.ValidacaoFiscalService;
import br.com.ithappens.lib.task.model.Arguments;
import br.com.ithappens.lib.task.service.task.ITask;
import br.com.ithappens.lib.task.service.task.TaskRunner;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.mockito.Mockito.mock;

public class ValidacaoTaskTest {

    private ITask validacaoTask;
    private TaskRunner runner;
    private ValidacaoFiscalService validacaoFiscalService;

    @Before
    public void before(){
        validacaoFiscalService = mock( ValidacaoFiscalService.class );

        validacaoTask = new ValidacaoTask(validacaoFiscalService);
    }

    @Test
    public void testValidacao(){

        Arguments args = new Arguments();
        args.getParams().put("filial", "7");
        args.getParams().put("datamovimento", "2020-04-02");
        args.getParams().put("revalidar", "true");

        validacaoTask.run(args);

        Mockito.verify(validacaoFiscalService, Mockito.times(1)).startReValidacao(ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(LocalDate.class));
    }

}
