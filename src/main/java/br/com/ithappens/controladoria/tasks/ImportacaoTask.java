package br.com.ithappens.controladoria.tasks;

import br.com.ithappens.controladoria.service.importacao.ProcessoNfceService;
import br.com.ithappens.controladoria.service.importacao.ProcessoNfeService;
import br.com.ithappens.lib.task.annotation.Task;
import br.com.ithappens.lib.task.model.Arguments;
import br.com.ithappens.lib.task.service.task.ITask;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Task(name = "integracao-dados-fiscais",
      description="Importação dos dados do processo de Nfc-e")
public class ImportacaoTask implements ITask {

    private final ProcessoNfceService nfceService;
    private final ProcessoNfeService nfeService;

    public ImportacaoTask(ProcessoNfceService nfceService, ProcessoNfeService nfeService) {
        this.nfceService = nfceService;
        this.nfeService = nfeService;
    }

    @Override
    public void run(Arguments args) {

        String argEmpresa       = args.get("empresa");
        String argFilial        = args.get("filial");
        String argDataMovimento = args.get("datamovimento");

        LocalDate dataMovimento;
        if (argDataMovimento.isEmpty())  { dataMovimento = LocalDate.now().minusDays(2); } else {
        dataMovimento = LocalDate.parse(argDataMovimento, DateTimeFormatter.ofPattern("yyyy-MM-dd")); }

        //PROCESSO DE NFC-E
        nfceService.startImportacao(argEmpresa, argFilial, dataMovimento);
        //PROCESSO DE NFE
        nfeService.startImportacao(argEmpresa, argFilial, dataMovimento);
    }

}
