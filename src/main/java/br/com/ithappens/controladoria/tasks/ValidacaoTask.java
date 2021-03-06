package br.com.ithappens.controladoria.tasks;

import br.com.ithappens.controladoria.service.validacao.ValidacaoFiscalService;
import br.com.ithappens.lib.task.annotation.Task;
import br.com.ithappens.lib.task.model.Arguments;
import br.com.ithappens.lib.task.service.task.ITask;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Task(name = "validacao-dados-fiscais",
      //cron = "0 4 * * *",
      description="Validação dos dados fiscais")
public class ValidacaoTask implements ITask {

    private final ValidacaoFiscalService validacaoFiscalService;

    public ValidacaoTask(ValidacaoFiscalService validacaoFiscalService) {
        this.validacaoFiscalService = validacaoFiscalService;
    }

    @Override
    public void run(Arguments args) {

        String argEmpresa       = args.get("empresa");
        String argFilial        = args.get("filial");
        String argDataMovimento = args.get("datamovimento");
        String argRevalidar     = args.get("revalidar");

        LocalDate dataMovimento;
        if (argDataMovimento.isEmpty())  { dataMovimento = LocalDate.now().minusDays(2); } else {
            dataMovimento = LocalDate.parse(argDataMovimento, DateTimeFormatter.ofPattern("yyyy-MM-dd")); }

        boolean revalida = false;
        if (!argRevalidar.isEmpty()) Boolean.parseBoolean(argRevalidar);

        validacaoFiscalService.startValidacao(argEmpresa, argFilial, dataMovimento);
        if (revalida) validacaoFiscalService.startReValidacao(argEmpresa, argFilial, dataMovimento);

    }
}
