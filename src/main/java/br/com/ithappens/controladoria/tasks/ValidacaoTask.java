package br.com.ithappens.controladoria.tasks;

import br.com.ithappens.controladoria.service.validacao.ValidacaoFiscalService;
import br.com.ithappens.lib.task.annotation.Task;
import br.com.ithappens.lib.task.model.Arguments;
import br.com.ithappens.lib.task.service.task.ITask;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Task(name = "validacao-dados-fiscais",
      description="Validação dos dados fiscais")
public class ValidacaoTask implements ITask {

    @Autowired
    private ValidacaoFiscalService validacaoFiscalService;

    @Override
    public void run(Arguments args) {

        String argEmpresa       = args.get("empresa");
        String argFilial        = args.get("filial");
        String argDataMovimento = args.get("datamovimento");

        LocalDate dataMovimento;
        if (argDataMovimento.isEmpty())  { dataMovimento = LocalDate.now().minusDays(2); } else {
            dataMovimento = LocalDate.parse(argDataMovimento, DateTimeFormatter.ofPattern("yyyy-MM-dd")); }

        validacaoFiscalService.startValidacaoFiscal(argEmpresa, argFilial, dataMovimento);
    }
}
