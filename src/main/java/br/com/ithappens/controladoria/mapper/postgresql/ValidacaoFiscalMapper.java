package br.com.ithappens.controladoria.mapper.postgresql;

import br.com.ithappens.controladoria.model.ValidacaoFiscal;
import br.com.ithappens.controladoria.model.enums.StatusValidacao;
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
                                                   @Param("idIntegrador") int idIntegrador,
                                                   @Param("serie") String serie);

    List<ValidacaoFiscal> recuperarValidacaoFiscalPeriodo(@Param("idFilial") UUID idFilial,
                                                          @Param("idIntegrador") int idIntegrador,
                                                          @Param("dataIni") LocalDate dataIni,
                                                          @Param("dataFim") LocalDate dataFim,
                                                          @Param("status") StatusValidacao status);

    Boolean insertValidacaoFiscal(@Param("loteLst") List<ValidacaoFiscal> loteLst);

}
