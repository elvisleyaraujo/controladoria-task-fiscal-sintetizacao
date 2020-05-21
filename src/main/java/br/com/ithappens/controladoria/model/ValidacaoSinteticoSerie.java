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
@EqualsAndHashCode(of = "id")
public class ValidacaoSinteticoSerie {

    private UUID id;
    private Filial filial;
    private Empresa empresa;
    private String serie;
    private LocalDateTime dataIntegracao;
    private LocalDate dataMovimento;
    private TipoOperacao tipoOperacao;
    private TipoIntegrador tipoIntegrador;
    private StatusValidacao status;
}