package br.com.ithappens.controladoria.mapper.postgresql;

import br.com.ithappens.controladoria.model.Filial;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FilialMapper {

    List<Filial> recuperarFilial(@Param("codigoEmpresa") String codigoEmpresa,
                                 @Param("codigoFilial") String codigoFilial);

}
