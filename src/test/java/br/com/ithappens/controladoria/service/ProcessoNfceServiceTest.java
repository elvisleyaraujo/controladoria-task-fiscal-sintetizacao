package br.com.ithappens.controladoria.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class ProcessoNfceServiceTest {

    private final ProcessoNfceService processoNfceService = new ProcessoNfceService();

    @Before
    public void before(){
    }

    @Test
    public void importAndSave() {

        processoNfceService.importAndSave("2", "7", LocalDate.now());


        //System.out.println(future.toString());

    }

}
