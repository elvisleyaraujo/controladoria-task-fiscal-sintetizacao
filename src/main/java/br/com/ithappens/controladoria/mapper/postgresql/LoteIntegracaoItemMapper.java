package br.com.ithappens.controladoria.mapper.postgresql;

import br.com.ithappens.controladoria.model.LoteIntegracaoItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface LoteIntegracaoItemMapper {

    Boolean insertLoteIntegracaoItem(@Param("loteLst") List<LoteIntegracaoItem> loteLst);

}
