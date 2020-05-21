package br.com.ithappens.controladoria.service;

import br.com.ithappens.controladoria.mapper.postgresql.FilialMapper;
import br.com.ithappens.controladoria.model.Filial;
import br.com.ithappens.controladoria.service.importacao.ProcessoNfceService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProcessoNfceServiceTest {

    @Autowired
    private ProcessoNfceService processoNfceService;
    @Autowired
    private FilialMapper filialMapper;

    @Before
    public void before(){
    }

    @Test
    public void testFindFiliais(){
        List<Filial> filialList = filialMapper.recuperarFilial("2", "7");
        Assertions.assertThat(filialList.size()).isEqualTo(1);
        Assertions.assertThat(filialList.get(0).getCodigo()).isEqualTo("7");
    }


}
