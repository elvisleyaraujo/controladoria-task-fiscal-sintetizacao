package br.com.ithappens.controladoria.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "cd00b_id")
public class Filial {

    private Long cd00b_id;
    private Long cd00b_numero;
    private Empresa cd00b_id_empresa;
    private String cd00b_razao_social;

}
