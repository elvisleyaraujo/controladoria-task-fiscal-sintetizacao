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

    private Long id;
    private String descricao;
    private String sigla;
    private Modulo moduloPadrao;
    private UUID uuidchave;

}
