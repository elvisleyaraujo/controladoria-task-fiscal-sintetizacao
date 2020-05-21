package br.com.ithappens.controladoria.mapper.postgresql;

import br.com.ithappens.controladoria.model.LoteSinteticoSerie;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@Mapper
public interface LoteSinteticoSerieMapper {

    List<LoteSinteticoSerie> recuperarSinteticoSerie(@Param("codigoFilial") String codigoFilial,
                                                     @Param("dataMovimento")LocalDate dataMovimento,
                                                     @Param("idIntegrador") int idIntegrador);

    Boolean insertLoteSinteticoSerie(@Param("loteLst") List<LoteSinteticoSerie> loteLst);

}
