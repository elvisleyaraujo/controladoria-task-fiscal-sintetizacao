package br.com.ithappens.controladoria.mapper;

import br.com.ithappens.controladoria.model.LoteIntegracaoItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@Mapper
public interface ProcessoNfceMapper {

    public List<LoteIntegracaoItem> recuperarFiscal(@Param("idfilial") Long idFilial,
                                                    @Param("datamovimento")LocalDate datamovimento);

    public List<LoteIntegracaoItem> recuperarEstoque(@Param("idfilial") Long idFilial,
                                                     @Param("datamovimento") LocalDate datamovimento);

    public List<LoteIntegracaoItem> recuperarFinanceiro(@Param("idfilial") Long idFilial,
                                                        @Param("datamovimento") LocalDate datamovimento);
}
