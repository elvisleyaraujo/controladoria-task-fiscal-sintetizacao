package br.com.ithappens.controladoria.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class Filial {

    private Long id;
    private Long numero;
    private Empresa empresa;
    private String razaoSocial;

}
