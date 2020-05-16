package br.com.ithappens.controladoria.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "cd11_id")
public class ModuloIntegrador {

    private Long cd11_id;
    private String cd11_desc;
    private Boolean cd11_ativo;

}
