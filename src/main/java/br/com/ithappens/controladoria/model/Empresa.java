package br.com.ithappens.controladoria.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Empresa {

    private UUID id;
    private String codigo;
    private String nome;
    private String cnpjRaiz;
    private Filial matriz;
    private Boolean ativo;

}
