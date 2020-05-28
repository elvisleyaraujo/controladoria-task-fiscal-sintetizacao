package br.com.ithappens.controladoria.service.validacao;

import br.com.ithappens.controladoria.mapper.postgresql.ConfiguracaoIntegradorMapper;
import br.com.ithappens.controladoria.mapper.postgresql.FilialMapper;
import br.com.ithappens.controladoria.mapper.postgresql.IntegracaoFiscalMapper;
import br.com.ithappens.controladoria.mapper.postgresql.ValidacaoFiscalMapper;
import br.com.ithappens.controladoria.model.*;
import br.com.ithappens.controladoria.model.enums.StatusValidacao;
import br.com.ithappens.controladoria.model.enums.TipoOperacao;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ValidacaoFiscalServiceTest {

    private ValidacaoFiscalMapper validacaoFiscalMapper;
    private IntegracaoFiscalMapper integracaoFiscalMapper;
    private FilialMapper filialMapper;
    private ConfiguracaoIntegradorMapper configuracaoIntegradorMapper;
    private ValidacaoFiscalService validacaoFiscalService;

    List<IntegracaoFiscal> integracaoFiscalList = new ArrayList<>();
    List<ValidacaoFiscal> validacaoFiscalList = new ArrayList<>();
    List<ConfiguracaoIntegrador> configuracaoIntegradorList = new ArrayList<>();
    Filial filial = new Filial();
    List<Filial> listFilial = new ArrayList<>();

    public void setFilial(){
        Empresa empresa = new Empresa();
        empresa.setCodigo("2");
        empresa.setId(UUID.fromString("8d446bdd-b251-45aa-9869-f38de7833940"));
        filial.setCodigo("7");
        filial.setId(UUID.fromString("5e3e607e-be01-4b34-80d8-3abfdc3ebc4d"));
        filial.setEmpresa(empresa);
        listFilial.add(filial);
    }

    public void setListaIntegracaoFiscal(){
        IntegracaoFiscal integracaoFiscal = new IntegracaoFiscal();
        integracaoFiscal.setId(UUID.randomUUID());
        integracaoFiscal.setValor(100);
        integracaoFiscal.setFilial(filial);
        integracaoFiscal.setTipoOperacao(TipoOperacao.SAIDA);
        integracaoFiscal.setDataMovimento(LocalDate.now());
        integracaoFiscal.setEmpresa(filial.getEmpresa());
        integracaoFiscal.setTipoIntegrador(getTipoIntegrador());
        integracaoFiscal.setModulo(getModulo(3L));
        integracaoFiscal.setSerie("300");

        IntegracaoFiscal integracaoEstoque = new IntegracaoFiscal();
        integracaoEstoque.setId(UUID.randomUUID());
        integracaoEstoque.setValor(80);
        integracaoEstoque.setFilial(filial);
        integracaoEstoque.setTipoOperacao(TipoOperacao.SAIDA);
        integracaoEstoque.setDataMovimento(LocalDate.now());
        integracaoEstoque.setEmpresa(filial.getEmpresa());
        integracaoEstoque.setTipoIntegrador(getTipoIntegrador());
        integracaoEstoque.setModulo(getModulo(2L));
        integracaoEstoque.setSerie("300");

        integracaoFiscalList.add(integracaoFiscal);
        integracaoFiscalList.add(integracaoEstoque);
    }

    public void setListaConfiguracaoIntegrador(){
        ConfiguracaoIntegrador configuracaoEstoque = new ConfiguracaoIntegrador();
        configuracaoEstoque.setUuid(UUID.randomUUID());
        configuracaoEstoque.setModulo(getModulo(2L));
        configuracaoEstoque.setTipoIntegrador(getTipoIntegrador());

        ConfiguracaoIntegrador configuracaoFiscal = new ConfiguracaoIntegrador();
        configuracaoFiscal.setUuid(UUID.randomUUID());
        configuracaoFiscal.setModulo(getModulo(3L));
        configuracaoFiscal.setTipoIntegrador(getTipoIntegrador());


        configuracaoIntegradorList.add(configuracaoEstoque);
    }

    public void setListaValidacaoFiscal(){
        ValidacaoFiscal validacaoFiscal = new ValidacaoFiscal();

        validacaoFiscal.setStatus(StatusValidacao.INCONSISTENTE);
        validacaoFiscal.setDataMovimento(LocalDate.now());
        validacaoFiscal.setFilial(filial);
        validacaoFiscal.setSerie("300");
        validacaoFiscal.setTipoIntegrador(getTipoIntegrador());

        validacaoFiscalList.add(validacaoFiscal);
    }

    public TipoIntegrador getTipoIntegrador(){
        TipoIntegrador tipoIntegrador = new TipoIntegrador();
        tipoIntegrador.setId(1L);
        tipoIntegrador.setDescricao("TESTE");

        return tipoIntegrador;
    }

    public Modulo getModulo(Long id){
        Modulo modulo = new Modulo();
        modulo.setId(id);
        return modulo;
    }

    @Before
    public void before(){
        configuracaoIntegradorMapper = Mockito.mock( ConfiguracaoIntegradorMapper.class );
        integracaoFiscalMapper = Mockito.mock( IntegracaoFiscalMapper.class );
        filialMapper = Mockito.mock( FilialMapper.class );
        validacaoFiscalMapper = Mockito.mock( ValidacaoFiscalMapper.class );

        setFilial();
        setListaIntegracaoFiscal();
        setListaConfiguracaoIntegrador();
        setListaValidacaoFiscal();


        validacaoFiscalService = new ValidacaoFiscalService(validacaoFiscalMapper,
                                                            integracaoFiscalMapper,
                                                            filialMapper,
                                                            configuracaoIntegradorMapper);
    }

    @Test
    public void testValidaProcesso(){
        List<ValidacaoFiscal> listZero = new ArrayList<>();

        Mockito.when( validacaoFiscalMapper.insertValidacaoFiscal( ArgumentMatchers.anyList() ) ).thenReturn(true);
        Mockito.when( validacaoFiscalMapper.recuperarValidacaoFiscal(ArgumentMatchers.any(UUID.class),
                ArgumentMatchers.any(LocalDate.class),
                ArgumentMatchers.anyInt(),
                ArgumentMatchers.anyString())).thenReturn(listZero);
        Mockito.when( filialMapper.recuperarFilial(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(listFilial);
        Mockito.when( configuracaoIntegradorMapper.recuperarConfiguracaoIntegrador(ArgumentMatchers.anyInt())).thenReturn(configuracaoIntegradorList);
        Mockito.when( integracaoFiscalMapper.recuperarIntegracaoFiscal(ArgumentMatchers.any(UUID.class),
                ArgumentMatchers.any(LocalDate.class),
                ArgumentMatchers.anyInt(),
                ArgumentMatchers.anyString())).thenReturn(integracaoFiscalList);

        validacaoFiscalService.startValidacao("2", "7", LocalDate.now());

        Mockito.verify(filialMapper, Mockito.times(1)).recuperarFilial(ArgumentMatchers.anyString(), ArgumentMatchers.anyString());
        Mockito.verify(validacaoFiscalMapper, Mockito.times(2)).recuperarValidacaoFiscal(ArgumentMatchers.any(UUID.class),
                ArgumentMatchers.any(LocalDate.class),
                ArgumentMatchers.anyInt(),
                ArgumentMatchers.anyString());
        Mockito.verify(validacaoFiscalMapper, Mockito.times(2)).insertValidacaoFiscal(ArgumentMatchers.anyList());
        Mockito.verify(configuracaoIntegradorMapper, Mockito.times(2)).recuperarConfiguracaoIntegrador(ArgumentMatchers.anyInt());
        Mockito.verify(integracaoFiscalMapper, Mockito.times(2)).recuperarIntegracaoFiscal(ArgumentMatchers.any(UUID.class),
                ArgumentMatchers.any(LocalDate.class),
                ArgumentMatchers.anyInt(),
                ArgumentMatchers.anyString());
    }

    @Test
    public void testStartReValidacaoComConflito(){
        List<ValidacaoFiscal> listZero = new ArrayList<>();

        Mockito.when( validacaoFiscalMapper.recuperarValidacaoFiscalPeriodo(ArgumentMatchers.any(UUID.class),
                ArgumentMatchers.anyInt(),
                ArgumentMatchers.any(LocalDate.class),
                ArgumentMatchers.any(LocalDate.class),
                ArgumentMatchers.any(StatusValidacao.class))).thenReturn(validacaoFiscalList);
        Mockito.when( validacaoFiscalMapper.insertValidacaoFiscal( ArgumentMatchers.anyList() ) ).thenReturn(true);
        Mockito.when( validacaoFiscalMapper.recuperarValidacaoFiscal(ArgumentMatchers.any(UUID.class),
                ArgumentMatchers.any(LocalDate.class),
                ArgumentMatchers.anyInt(),
                ArgumentMatchers.anyString())).thenReturn(listZero);
        Mockito.when( filialMapper.recuperarFilial(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(listFilial);
        Mockito.when( configuracaoIntegradorMapper.recuperarConfiguracaoIntegrador(ArgumentMatchers.anyInt())).thenReturn(configuracaoIntegradorList);
        Mockito.when( integracaoFiscalMapper.recuperarIntegracaoFiscal(ArgumentMatchers.any(UUID.class),
                ArgumentMatchers.any(LocalDate.class),
                ArgumentMatchers.anyInt(),
                ArgumentMatchers.anyString())).thenReturn(integracaoFiscalList);

        validacaoFiscalService.startReValidacao("2", "7", LocalDate.now());

        Mockito.verify(filialMapper, Mockito.times(1)).recuperarFilial(ArgumentMatchers.anyString(), ArgumentMatchers.anyString());
        Mockito.verify(validacaoFiscalMapper, Mockito.times(2)).recuperarValidacaoFiscal(ArgumentMatchers.any(UUID.class),
                ArgumentMatchers.any(LocalDate.class),
                ArgumentMatchers.anyInt(),
                ArgumentMatchers.anyString());
        Mockito.verify(validacaoFiscalMapper, Mockito.times(2)).insertValidacaoFiscal(ArgumentMatchers.anyList());
        Mockito.verify(configuracaoIntegradorMapper, Mockito.times(2)).recuperarConfiguracaoIntegrador(ArgumentMatchers.anyInt());
        Mockito.verify(integracaoFiscalMapper, Mockito.times(2)).recuperarIntegracaoFiscal(ArgumentMatchers.any(UUID.class),
                ArgumentMatchers.any(LocalDate.class),
                ArgumentMatchers.anyInt(),
                ArgumentMatchers.anyString());
    }



}
