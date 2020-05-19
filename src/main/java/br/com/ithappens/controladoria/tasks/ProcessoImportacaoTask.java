package br.com.ithappens.controladoria.tasks;

import br.com.ithappens.controladoria.service.nfce.ProcessoNfceService;
import br.com.ithappens.lib.task.annotation.Task;
import br.com.ithappens.lib.task.model.Arguments;
import br.com.ithappens.lib.task.service.task.ITask;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@Task(name = "fiscal-processo-nfce",
      description="Importação dos dados do processo de Nfc-e")
public class ProcessoImportacaoTask implements ITask {

    @Autowired
    private ProcessoNfceService processoNfceService;

    @Override
    public void run(Arguments args) {

        LocalDate dataMovimento = LocalDate.of(2020,05,02);

        //PROCESSO DE NFC-E
        processoNfceService.importAndSave("2","", dataMovimento);

        //PROCESSO DE NFE
        
    }

}
