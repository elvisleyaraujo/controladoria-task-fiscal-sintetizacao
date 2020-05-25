package br.com.ithappens.controladoria.service.validacao;

import br.com.ithappens.controladoria.mapper.postgresql.ConfiguracaoIntegradorMapper;
import br.com.ithappens.controladoria.mapper.postgresql.FilialMapper;
import br.com.ithappens.controladoria.mapper.postgresql.IntegracaoFiscalMapper;
import br.com.ithappens.controladoria.mapper.postgresql.ValidacaoFiscalMapper;
import br.com.ithappens.controladoria.mapper.sqlserver.ProcessoNfceMapper;
import br.com.ithappens.controladoria.model.Empresa;
import br.com.ithappens.controladoria.model.Filial;
import br.com.ithappens.controladoria.model.IntegracaoFiscal;
import br.com.ithappens.controladoria.service.importacao.ProcessoNfceService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ValidacaoFiscalServiceTest {

    private ValidacaoFiscalMapper validacaoFiscalMapper;
    private IntegracaoFiscalMapper integracaoFiscalMapper;
    private FilialMapper filialMapper;
    private ConfiguracaoIntegradorMapper configuracaoIntegradorMapper;
    private ValidacaoFiscalService validacaoFiscalService;

    List<IntegracaoFiscal> integracaoFiscalList = new ArrayList<>();
    Filial filial = new Filial();

    @Before
    public void before(){
        configuracaoIntegradorMapper = Mockito.mock( ConfiguracaoIntegradorMapper.class );
        integracaoFiscalMapper = Mockito.mock( IntegracaoFiscalMapper.class );
        filialMapper = Mockito.mock( FilialMapper.class );
        validacaoFiscalMapper = Mockito.mock( ValidacaoFiscalMapper.class );

        integracaoFiscalList.add(new IntegracaoFiscal());
        Empresa empresa = new Empresa();
        empresa.setCodigo("2");
        filial.setCodigo("7");
        filial.setEmpresa(empresa);

        validacaoFiscalService = new ValidacaoFiscalService(validacaoFiscalMapper,
                                                            integracaoFiscalMapper,
                                                            filialMapper,
                                                            configuracaoIntegradorMapper);
    }

    @Test
    public void startValidacaoTest(){

        validacaoFiscalService.startValidacao("2", "7", LocalDate.now());

    }



}
