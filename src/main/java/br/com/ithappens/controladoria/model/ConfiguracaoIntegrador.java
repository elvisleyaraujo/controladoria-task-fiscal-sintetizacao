package br.com.ithappens.controladoria.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@EqualsAndHashCode(of = "uuid")
@NoArgsConstructor
@AllArgsConstructor
public class ConfiguracaoIntegrador {

    private UUID uuid;
    private TipoIntegrador tipoIntegrador;
    private Modulo modulo;
    private int tipoOcorrencia;
    private UUID uuidAcao;

}
