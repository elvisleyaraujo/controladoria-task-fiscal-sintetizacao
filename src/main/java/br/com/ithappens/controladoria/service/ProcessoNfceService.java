package br.com.ithappens.controladoria.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessoNfceService {

    public boolean importaNfceFiscal(){
        log.info("IMPORTAÇÃO FISCAL A SER IMPLEMENTADO");
        return false;
    };

    public boolean importaNfceFinanceiro(){
        log.info("IMPORTAÇÃO FINANCEIRO A SER IMPLEMENTADO");
        return false;
    };

    public boolean importaNfceEstoque(){
        log.info("IMPORTAÇÃO ESTOQUE A SER IMPLEMENTADO");
        return false;
    };

    public boolean importaNfcePDV(){
        log.info("IMPORTAÇÃO PDV A SER IMPLEMENTADO");
        return false;
    };
}
