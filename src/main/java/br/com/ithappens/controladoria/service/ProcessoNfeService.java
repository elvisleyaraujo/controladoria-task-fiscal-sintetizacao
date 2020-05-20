package br.com.ithappens.controladoria.service;

import br.com.ithappens.controladoria.mapper.postgresql.FilialMapper;
import br.com.ithappens.controladoria.model.Filial;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class ProcessoNfeService {

    @Autowired
    private FilialMapper filialMapper;

    public void importAndSave(String codigoEmpresa, String codigoFilial, LocalDate dataMovimento){
        List<Filial> filialList = filialMapper.recuperarFilial(codigoEmpresa, codigoFilial);

    }


}
