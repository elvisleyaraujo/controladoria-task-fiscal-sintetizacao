package br.com.ithappens.controladoria.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "cd01_id")
public class Empresa {

    private Long cd01_id;
    private LocalDateTime cd01_dt;
    private Long cd01_cnpj_raiz;
    private Long cd01_numero;
    private Boolean cd01_ativo;
    private Long cd01_id_grupo_operacao;

}
