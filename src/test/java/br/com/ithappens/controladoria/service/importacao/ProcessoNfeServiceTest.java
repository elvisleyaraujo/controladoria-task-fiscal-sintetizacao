package br.com.ithappens.controladoria.service.importacao;

import br.com.ithappens.controladoria.mapper.postgresql.FilialMapper;
import br.com.ithappens.controladoria.mapper.postgresql.IntegracaoFiscalMapper;
import br.com.ithappens.controladoria.mapper.sqlserver.ProcessoNfeMapper;
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

public class ProcessoNfeServiceTest {

    private ProcessoNfeService processoNfeService;

    private ProcessoNfeMapper processoNfeMapper;
    private IntegracaoFiscalMapper integracaoFiscalMapper;
    private FilialMapper filialMapper;

    Filial filial = new Filial();
    List<IntegracaoFiscal> integracaoFiscalList = new ArrayList<>();

    @Before
    public void before(){
        processoNfeMapper = Mockito.mock( ProcessoNfeMapper.class );
        integracaoFiscalMapper = Mockito.mock( IntegracaoFiscalMapper.class );
        filialMapper = Mockito.mock( FilialMapper.class );

        integracaoFiscalList.add(new IntegracaoFiscal());
        Empresa empresa = new Empresa();
        empresa.setCodigo("2");
        filial.setCodigo("7");
        filial.setEmpresa(empresa);

        processoNfeService = new ProcessoNfeService(processoNfeMapper,integracaoFiscalMapper,filialMapper);
    }


    @Test
    public void testImportarFiscal(){
        Mockito.when( integracaoFiscalMapper.insertIntegracaoFiscal(integracaoFiscalList)).thenReturn(true);
        Mockito.when( processoNfeMapper.recuperarFiscal(ArgumentMatchers.anyString(),ArgumentMatchers.any(LocalDate.class)) ).thenReturn(integracaoFiscalList);

        Assertions.assertThat( processoNfeService.importacaoFiscal( filial, LocalDate.now() ) ).isTrue();
    }

    @Test
    public void testImportacaoFinanceiro(){
        Mockito.when( integracaoFiscalMapper.insertIntegracaoFiscal(integracaoFiscalList)).thenReturn(true);
        Mockito.when( processoNfeMapper.recuperarFinanceiro(ArgumentMatchers.anyString(),ArgumentMatchers.any(LocalDate.class)) ).thenReturn(integracaoFiscalList);

        Assertions.assertThat( processoNfeService.importacaoFinanceiro( filial, LocalDate.now() ) ).isTrue();
    }

    @Test
    public void testImportacaoEstoque(){
        Mockito.when( integracaoFiscalMapper.insertIntegracaoFiscal(integracaoFiscalList)).thenReturn(true);
        Mockito.when( processoNfeMapper.recuperarEstoque(ArgumentMatchers.anyString(),ArgumentMatchers.any(LocalDate.class)) ).thenReturn(integracaoFiscalList);

        Assertions.assertThat( processoNfeService.importacaoEstoque( filial, LocalDate.now() ) ).isTrue();
    }

    @Test
    public void testFindAndSave(){
        Assertions.assertThat( processoNfeService.findAndSave(filial, LocalDate.now()) ).isCompleted();
    }


}
