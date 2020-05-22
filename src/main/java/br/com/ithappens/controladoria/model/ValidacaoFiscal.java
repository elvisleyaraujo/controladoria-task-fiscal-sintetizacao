package br.com.ithappens.controladoria.model;

import br.com.ithappens.controladoria.model.enums.StatusValidacao;
import br.com.ithappens.controladoria.model.enums.TipoOperacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"filial", "empresa", "serie", "dataMovimento", "tipoOperacao", "tipoIntegrador"})
public class ValidacaoFiscal {

    private UUID id;
    private Filial filial;
    private Empresa empresa;
    private String serie;
    private LocalDateTime dataIntegracao;
    private LocalDate dataMovimento;
    private TipoOperacao tipoOperacao;
    private TipoIntegrador tipoIntegrador;
    private StatusValidacao status;

    public ValidacaoFiscal(IntegracaoFiscal integracaoFiscal){
        this.id = UUID.randomUUID();
        this.filial = integracaoFiscal.getFilial();
        this.empresa = integracaoFiscal.getEmpresa();
        this.serie = integracaoFiscal.getSerie();
        this.dataMovimento = integracaoFiscal.getDataMovimento();
        this.dataIntegracao = LocalDateTime.now();
        this.tipoOperacao = integracaoFiscal.getTipoOperacao();
        this.tipoIntegrador = integracaoFiscal.getTipoIntegrador();
    }

}