package br.com.ithappens.controladoria.mapper.postgresql;

import br.com.ithappens.controladoria.model.ValidacaoFiscal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Mapper
@Repository
public interface ValidacaoFiscalMapper {

    List<ValidacaoFiscal> recuperarValidacaoFiscal(@Param("idFilial") UUID idFilial,
                                                   @Param("dataMovimento") LocalDate dataMovimento,
                                                   @Param("idIntegrador") int idIntegrador);

    Boolean insertValidacaoFiscal(@Param("loteLst") List<ValidacaoFiscal> loteLst);

}
