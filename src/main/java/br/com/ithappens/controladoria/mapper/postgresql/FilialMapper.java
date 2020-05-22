package br.com.ithappens.controladoria.mapper.postgresql;

import br.com.ithappens.controladoria.model.Filial;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FilialMapper {

    List<Filial> recuperarFilial(@Param("codigoEmpresa") String codigoEmpresa,
                                 @Param("codigoFilial") String codigoFilial);

}
