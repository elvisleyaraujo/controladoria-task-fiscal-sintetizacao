package br.com.ithappens.controladoria.mapper.sqlserver;

import br.com.ithappens.controladoria.model.LoteSinteticoSerie;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@Mapper
public interface ProcessoNfceMapper {

    List<LoteSinteticoSerie> recuperarFiscal(@Param("codigoFilial") String codigoFilial,
                                             @Param("datamovimento") LocalDate datamovimento);

    List<LoteSinteticoSerie> recuperarEstoque(@Param("codigoFilial") String codigoFilial,
                                              @Param("datamovimento") LocalDate datamovimento);

}
