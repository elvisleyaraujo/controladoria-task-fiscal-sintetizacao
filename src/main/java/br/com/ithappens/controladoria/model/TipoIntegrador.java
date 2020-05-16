package br.com.ithappens.controladoria.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@EqualsAndHashCode( of = "cd18_id")
@NoArgsConstructor
@AllArgsConstructor
public class TipoIntegrador {

    private Long cd18_id;
    private String cd18_desc;
    private String cd18_sigla;
    private ModuloIntegrador cd18_id_modulo_padrao;
    private UUID cd18_uuid_chave;

}
