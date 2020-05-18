package br.com.ithappens.controladoria.model;

import lombok.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Filial {

    private UUID id;
    private String codigo;
    private Empresa empresa;
    private Boolean ativo;

}
