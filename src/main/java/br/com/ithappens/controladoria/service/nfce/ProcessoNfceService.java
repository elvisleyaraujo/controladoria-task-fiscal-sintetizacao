package br.com.ithappens.controladoria.service.nfce;

import br.com.ithappens.controladoria.mapper.postgresql.FilialMapper;
import br.com.ithappens.controladoria.model.Filial;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class ProcessoNfceService {

    @Autowired
    private FilialMapper filialMapper;
    @Autowired
    private NfceImportacaoEstoque impEstoque;
    @Autowired
    private NfceImportacaoFiscal impFiscal;
    @Autowired
    private NfceImportacaoFinanceiro impFinanceiro;
    @Autowired
    private NfceImportacaoPDV impPDV;

    public void importAndSave(String codigoEmpresa, String codigoFilial, LocalDate dataMovimento){
        List<Filial> filialList = filialMapper.recuperarFilial(codigoEmpresa, codigoFilial);
        impEstoque.importar(filialList, dataMovimento);
        impFiscal.importar(filialList, dataMovimento);
        impFinanceiro.importar(filialList, dataMovimento);
        impPDV.importar(filialList, dataMovimento);
    }

}
