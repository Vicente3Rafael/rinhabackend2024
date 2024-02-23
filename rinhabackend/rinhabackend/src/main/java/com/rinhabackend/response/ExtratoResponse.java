package com.rinhabackend.response;

import com.rinhabackend.entity.Saldo;
import com.rinhabackend.entity.Transacao;
import lombok.Data;

import java.util.List;

@Data
public class ExtratoResponse {

    private Saldo saldo;
    private List<Transacao> transacoes;
}
