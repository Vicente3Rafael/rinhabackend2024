package com.rinhabackend.controller;

import com.rinhabackend.entity.Saldo;
import com.rinhabackend.entity.Transacao;
import com.rinhabackend.entity.User;
import com.rinhabackend.repository.TestRepository;
import com.rinhabackend.request.TransacaoRequest;
import com.rinhabackend.response.ExtratoResponse;
import com.rinhabackend.response.TransacaoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class RestCliente {

    @Autowired
    TestRepository repository;

    @PostMapping("/{id}/transacoes")
    public ResponseEntity<?> transacoes(@PathVariable int id, @RequestBody TransacaoRequest request) {
        TransacaoResponse response = new TransacaoResponse();

        Object object = processarTransacao(id, request);
        try {
            ResponseEntity responseEntity = (ResponseEntity) object;

            return responseEntity;
        }catch (Exception e) {
            response = (TransacaoResponse) object;
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/extrato")
    public ResponseEntity<?> transacoes(@PathVariable int id) {
        ExtratoResponse response = new ExtratoResponse();
        Object object = processarPedidoExtrato(id);
        try {
            ResponseEntity responseEntity = (ResponseEntity) object;

            return responseEntity;
        }catch (Exception e) {
            response = (ExtratoResponse) object;
        }

        return ResponseEntity.ok(response);
    }

    public Object processarPedidoExtrato(int id) {
        User user = repository.getUserById(id);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }

        Saldo saldo = new Saldo(user);
        List<Transacao> transacoes = repository.getLastTenTransacoesBy(user.getId());

        ExtratoResponse response = new ExtratoResponse();
        response.setSaldo(saldo);
        response.setTransacoes(transacoes);

        return response;
    }

    public Object processarTransacao(int id, TransacaoRequest request) {
        String operacao = request.getTipo();
        if(!operacao.equals("c") && !operacao.equals("d")) {
            return ResponseEntity.badRequest().build();
        }

        User user = repository.getUserById(id);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }

        int limite = user.getLimite();
        int valor = request.getValor();
        int saldoInicial = user.getSaldoInicial();
        if(operacao.equals("d")) {
            saldoInicial = saldoInicial - valor;

            if(saldoInicial < 0 && (saldoInicial*-1) > limite) {
                return ResponseEntity.unprocessableEntity().build();
            }
        }else {
            saldoInicial = saldoInicial + valor;
        }

        TransacaoResponse response = new TransacaoResponse();
        response.setLimite(limite);
        response.setSaldo(saldoInicial);

        user.setSaldoInicial(saldoInicial);
        repository.updateUser(user);
        saveTransacao(request, user.getId());

        return response;
    }

    public void saveTransacao(TransacaoRequest transacaoRequest, int userId) {
        Transacao transacao = new Transacao(transacaoRequest);

        repository.saveTransacao(transacao, userId);
    }
}
