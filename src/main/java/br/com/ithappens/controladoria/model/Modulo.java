package br.com.ithappens.controladoria.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Modulo {

    private Long id;
    private String descricao;
    private Boolean ativo;

}
