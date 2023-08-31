package com.gineude.helpdesk.domains.enums;

public enum Status {

    ABERTO(0, "ABERTO"), ANDAMENTO(1, "ANDAMENTO"), ENCERRADO(2, "ENCERRADO");

    private Integer codigo;
    private String descricao;

    Status(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Status toEnum(Integer codigo) {
        if (codigo == null) {
            return null;
        }
        for (Status p : Status.values()) {
            if (codigo.equals(p.getCodigo())) {
                return p;
            }
        }
        throw new IllegalArgumentException("Status inválido!");
    }
}
