package br.com.ithappens.controladoria.tasks;

import br.com.ithappens.controladoria.service.ProcessoNfceService;
import br.com.ithappens.lib.task.annotation.Task;
import br.com.ithappens.lib.task.model.Arguments;
import br.com.ithappens.lib.task.service.task.ITask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.task.repository.TaskExecution;

@Task(name = "fiscal-processo-nfce",
      description="Importação dos dados do processo de Nfc-e")
public class ProcessoNFceTask implements ITask {

    @Autowired
    private ProcessoNfceService processoNfceService;

    @Override
    public void run(Arguments args) {

        processoNfceService.importaNfceFiscal();

    }

}
