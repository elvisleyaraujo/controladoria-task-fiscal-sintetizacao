package br.com.ithappens.controladoria.mapper.postgresql;

import br.com.ithappens.controladoria.model.ValidacaoSinteticoSerie;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ValidacaoSinteticoSerieMapper {

    Boolean insertValidacaoSinteticoSerie(@Param("loteLst") List<ValidacaoSinteticoSerie> loteLst);

}
