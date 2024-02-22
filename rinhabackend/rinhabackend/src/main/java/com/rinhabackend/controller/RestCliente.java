package com.rinhabackend.controller;

import com.rinhabackend.entity.User;
import com.rinhabackend.repository.TestRepository;
import com.rinhabackend.request.TransacaoRequest;
import com.rinhabackend.response.TransacaoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        return response;
    }
}
