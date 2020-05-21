package br.com.ithappens.controladoria.mapper.sqlserver;

import br.com.ithappens.controladoria.model.LoteIntegracaoItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Mapper
@Repository
public interface ProcessoNfeMapper {

    List<LoteIntegracaoItem> recuperarFiscal(@Param("codigoFilial") String codigoFilial,
                                             @Param("datamovimento") LocalDate datamovimento);


}
