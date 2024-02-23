package com.rinhabackend.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Saldo {

    private int total;
    private Date date;
    private int limite;

    public Saldo(User user) {
        this.total = user.getSaldoInicial();
        this.date = new Date();
        this.limite = user.getLimite();
    }
}
