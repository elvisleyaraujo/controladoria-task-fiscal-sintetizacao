package br.com.ithappens.controladoria.service.importacao;

import br.com.ithappens.controladoria.mapper.postgresql.FilialMapper;
import br.com.ithappens.controladoria.mapper.postgresql.IntegracaoFiscalMapper;
import br.com.ithappens.controladoria.mapper.sqlserver.ProcessoNfceMapper;
import br.com.ithappens.controladoria.model.Empresa;
import br.com.ithappens.controladoria.model.Filial;
import br.com.ithappens.controladoria.model.IntegracaoFiscal;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProcessoNfceServiceTest {

    private ProcessoNfceService processoNfceService;

    private ProcessoNfceMapper processoNfceMapper;
    private IntegracaoFiscalMapper integracaoFiscalMapper;
    private FilialMapper filialMapper;

    Filial filial = new Filial();
    List<IntegracaoFiscal> integracaoFiscalList = new ArrayList<>();

    @Before
    public void before(){
        processoNfceMapper = Mockito.mock( ProcessoNfceMapper.class );
        integracaoFiscalMapper = Mockito.mock( IntegracaoFiscalMapper.class );
        filialMapper = Mockito.mock( FilialMapper.class );

        integracaoFiscalList.add(new IntegracaoFiscal());
        Empresa empresa = new Empresa();
        empresa.setCodigo("2");
        filial.setCodigo("7");
        filial.setEmpresa(empresa);

        Mockito.when( integracaoFiscalMapper.insertIntegracaoFiscal(integracaoFiscalList)).thenReturn(true);

        processoNfceService = new ProcessoNfceService(processoNfceMapper,integracaoFiscalMapper,filialMapper);
    }

    @Test
    public void testImportarEstoqueSucesso(){
        Mockito.when( processoNfceMapper.recuperarEstoque(ArgumentMatchers.anyString(),ArgumentMatchers.any(LocalDate.class)) ).thenReturn(integracaoFiscalList);

        Assertions.assertThat( processoNfceService.importacaoEstoque( filial, LocalDate.now() ) ).isTrue();
    }

    @Test
    public void testImportarFiscalSucesso(){
        Mockito.when( processoNfceMapper.recuperarFiscal(ArgumentMatchers.anyString(),ArgumentMatchers.any(LocalDate.class)) ).thenReturn(integracaoFiscalList);

        Assertions.assertThat( processoNfceService.importacaoFiscal( filial, LocalDate.now() ) ).isTrue();
    }

    @Test
    public void testImportarFinanceiroSucesso(){
        Assertions.assertThat( processoNfceService.importacaoFinanceiro(filial, LocalDate.now()) ).isFalse();
    }

    @Test
    public void testImportarPDVSucesso(){
        Assertions.assertThat( processoNfceService.importacaoPDV(filial, LocalDate.now()) ).isFalse();
    }

    @Test
    public void testFindAndSave(){
        Assertions.assertThat( processoNfceService.findAndSave(filial, LocalDate.now()) ).isCompleted();
    }

}
