package br.com.ithappens.controladoria.service.procnfce;

import br.com.ithappens.controladoria.mapper.postgresql.FilialMapper;
import br.com.ithappens.controladoria.mapper.postgresql.LoteIntegracaoItemMapper;
import br.com.ithappens.controladoria.mapper.sqlserver.ProcessoNfceMapper;
import br.com.ithappens.controladoria.model.Filial;
import br.com.ithappens.controladoria.service.IProcessoImportacao;
import br.com.ithappens.controladoria.service.procnfce.ImportarEstoque;
import br.com.ithappens.controladoria.service.procnfce.ImportarFiscal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessoNfceService {

    @Autowired
    private FilialMapper filialMapper;
    @Autowired
    ProcessoNfceMapper processoNfceMapper;
    @Autowired
    LoteIntegracaoItemMapper loteIntegracaoItemMapper;

    public void importar(String codigoEmpresa, String codigoFilial, LocalDate dataMovimento){
        List<Filial> filialList = filialMapper.recuperarFilial(codigoEmpresa, codigoFilial);

        IProcessoImportacao impFiscal  = new ImportarFiscal(processoNfceMapper,loteIntegracaoItemMapper);
        impFiscal.importar(filialList, dataMovimento);
        IProcessoImportacao impEstoque = new ImportarEstoque(processoNfceMapper,loteIntegracaoItemMapper);
        impEstoque.importar(filialList, dataMovimento);
    }

}
