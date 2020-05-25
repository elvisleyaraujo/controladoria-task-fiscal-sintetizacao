package br.com.ithappens.controladoria.service;

import br.com.ithappens.controladoria.mapper.postgresql.FilialMapper;
import br.com.ithappens.controladoria.mapper.postgresql.IntegracaoFiscalMapper;
import br.com.ithappens.controladoria.mapper.sqlserver.ProcessoNfceMapper;
import br.com.ithappens.controladoria.model.Empresa;
import br.com.ithappens.controladoria.model.Filial;
import br.com.ithappens.controladoria.model.IntegracaoFiscal;
import br.com.ithappens.controladoria.service.importacao.ProcessoNfceService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProcessoNfceServiceTest {

    private  ProcessoNfceService processoNfceService;

    private  ProcessoNfceMapper processoNfceMapper;
    private  IntegracaoFiscalMapper integracaoFiscalMapper;
    private  FilialMapper filialMapper;

    @Before
    public void before(){
        processoNfceMapper = Mockito.mock( ProcessoNfceMapper.class );
        integracaoFiscalMapper = Mockito.mock( IntegracaoFiscalMapper.class );
        filialMapper = Mockito.mock( FilialMapper.class );
        List<IntegracaoFiscal> integracaoFiscalList = new ArrayList<>();
        integracaoFiscalList.add( new IntegracaoFiscal());
        Mockito.when(  integracaoFiscalMapper.insertIntegracaoFiscal( integracaoFiscalList )).thenReturn(true);
        Mockito.when( processoNfceMapper.recuperarEstoque(ArgumentMatchers.anyString(),ArgumentMatchers.any(LocalDate.class)) ).thenReturn( integracaoFiscalList );
        processoNfceService = new ProcessoNfceService(processoNfceMapper,integracaoFiscalMapper,filialMapper);
    }

    @Test
    public void testImportacaoEstoque(){
        Filial filial = new Filial();
        filial.setId(UUID.randomUUID());
        filial.setCodigo("1");
        Empresa empresa = new Empresa();
        empresa.setCodigo("1");
        filial.setEmpresa( empresa);

        LocalDate dataMovimento = LocalDate.now();
        Assertions.assertThat( processoNfceService.importacaoEstoque(filial,dataMovimento)).isTrue();
    }


}
