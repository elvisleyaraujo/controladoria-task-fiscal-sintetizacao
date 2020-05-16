package br.com.ithappens.controladoria.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@EqualsAndHashCode(of = "cd18a_uuid")
@NoArgsConstructor
@AllArgsConstructor
public class ConfiguracaoIntegrador {

    private UUID cd18a_uuid;
    private TipoIntegrador cd18a_id_tipo_integrador;
    private ModuloIntegrador cd18a_id_modulo_integracao;
    private int cd18a_tipo_ocorrencia;
    private UUID cd18a_uuid_acao;

}
