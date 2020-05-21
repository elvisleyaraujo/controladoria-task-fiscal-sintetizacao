package br.com.ithappens.controladoria.model.enums;

public enum StatusValidacao {

    NENHUM(0),
    INCONSISTENTE(1),
    CONSISTENTE(2);

    private final int id;

    StatusValidacao(int id) {
        this.id = id;
    }

    public static StatusValidacao valueOfId(Integer id) {
        for (StatusValidacao statusValidacao : StatusValidacao.values()) {
            if (statusValidacao.id == id) {
                return statusValidacao;
            }
        }
        return null;
    }

    public Integer getId() {
        return id;
    }
}
