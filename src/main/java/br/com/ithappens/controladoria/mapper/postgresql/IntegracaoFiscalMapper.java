package br.com.ithappens.controladoria.mapper.postgresql;

import br.com.ithappens.controladoria.model.IntegracaoFiscal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
@Mapper
public interface IntegracaoFiscalMapper {

    List<IntegracaoFiscal> recuperarIntegracaoFiscal(@Param("idFilial") UUID idFilial,
                                                     @Param("dataMovimento")LocalDate dataMovimento,
                                                     @Param("idIntegrador") int idIntegrador);

    Boolean insertIntegracaoFiscal(@Param("loteLst") List<IntegracaoFiscal> loteLst);

}
