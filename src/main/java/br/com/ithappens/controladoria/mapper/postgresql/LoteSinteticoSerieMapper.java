package br.com.ithappens.controladoria.mapper.postgresql;

import br.com.ithappens.controladoria.model.LoteSinteticoSerie;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface LoteSinteticoSerieMapper {

    Boolean insertLoteSinteticoSerie(@Param("loteLst") List<LoteSinteticoSerie> loteLst);

}
