package br.com.ithappens.controladoria.tasks;

import br.com.ithappens.lib.task.annotation.Task;
import br.com.ithappens.lib.task.model.Arguments;
import br.com.ithappens.lib.task.service.task.ITask;

@Task(name = "contr-dados-fiscais-validacao",
        description="Validação dos dados fiscais")
public class ValidacaoTask implements ITask {

    @Override
    public void run(Arguments args) {



    }
}
