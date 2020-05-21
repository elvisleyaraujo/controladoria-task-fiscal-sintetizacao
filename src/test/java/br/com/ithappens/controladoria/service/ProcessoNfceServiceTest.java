package br.com.ithappens.controladoria.service;

import br.com.ithappens.controladoria.mapper.postgresql.FilialMapper;
import br.com.ithappens.controladoria.model.Filial;
import br.com.ithappens.controladoria.model.LoteIntegracaoItem;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;

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

    @Test
    public void testFindNFCeEstoque() {
        Filial filial = filialMapper.recuperarFilial("", "7").get(0);
        LocalDate dataMovimento = LocalDate.parse("2020-05-02");
        List<LoteIntegracaoItem> loteLst = processoNfceService.recuperarEstoque(filial, dataMovimento);
        Assertions.assertThat(loteLst.size()).isEqualTo(38);
    }

    @Test
    public void testFindNFCeFiscal() {
        Filial filial = filialMapper.recuperarFilial("", "7").get(0);
        LocalDate dataMovimento = LocalDate.parse("2020-05-02");
        List<LoteIntegracaoItem> loteLst = processoNfceService.recuperarFiscal(filial, dataMovimento);
        Assertions.assertThat(loteLst.size()).isEqualTo(41);
    }
}
