package com.rinhabackend.entity;

import com.rinhabackend.request.TransacaoRequest;
import lombok.Data;

import java.util.Date;

@Data
public class Transacao {

    private int valor;
    private String tipo;
    private String descricao;
    private Date date;

    public Transacao() {

    }

    public Transacao(TransacaoRequest request) {
        this.valor = request.getValor();
        this.tipo = request.getTipo();
        this.descricao = request.getDescricao();
        this.date = new Date();
    }
}
