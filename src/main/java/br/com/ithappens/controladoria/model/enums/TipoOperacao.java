package br.com.ithappens.controladoria.model.enums;

public enum TipoOperacao {

    NENHUM(0),
    ENTRADA(1),
    SAIDA(2),
    CANCELADO_SAIDA(3),
    CANCELADO_ENTRADA(4);

    private final int id;

    TipoOperacao(int id) {
        this.id = id;
    }

    public static TipoOperacao valueOfId(Integer id) {
        for (TipoOperacao tipoOperacao : TipoOperacao.values()) {
            if (tipoOperacao.id == id) {
                return tipoOperacao;
            }
        }
        return null;
    }

    public Integer getId() {
        return id;
    }
}
