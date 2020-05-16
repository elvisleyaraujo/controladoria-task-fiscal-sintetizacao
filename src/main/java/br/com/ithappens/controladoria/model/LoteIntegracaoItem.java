package br.com.ithappens.controladoria.model;

import br.com.ithappens.controladoria.model.enums.TipoOperacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@EqualsAndHashCode (of = "ctl05a_id")
@NoArgsConstructor
@AllArgsConstructor
public class LoteIntegracaoItem {

    private UUID ctl05a_id;
    private UUID ctl05a_id_lote_int;
    private Filial ctl05a_id_filial;
    private Empresa ctl05a_id_empresa;
    private String ctl05a_serie;
    private LocalDateTime ctl05a_dt;
    private LocalDate ctl05a_dt_movimento;
    private TipoOperacao ctl05a_tip_oper;
    private double ctl05a_valor;
    private TipoIntegrador ctl05a_id_tipo_integrador;
    private ModuloIntegrador ctl05a_id_modulo;
    private UUID ctl05a_revisao;

}