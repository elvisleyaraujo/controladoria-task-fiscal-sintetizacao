package br.com.ithappens.controladoria.mapper.postgresql;

import br.com.ithappens.controladoria.model.ConfiguracaoIntegrador;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ConfiguracaoIntegradorMapper {

    List<ConfiguracaoIntegrador> recuperarConfiguracaoIntegrador(@Param("idTipoIntegrador") int idTipoIntegrador);

}
