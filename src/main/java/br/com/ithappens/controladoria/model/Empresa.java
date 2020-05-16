package br.com.ithappens.controladoria.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Empresa {

    private Long id;
    private LocalDateTime dataModificacao;
    private Long cnpjRaiz;
    private Long numero;
    private Boolean ativo;
    private Long idGrupoOperacao;

}
