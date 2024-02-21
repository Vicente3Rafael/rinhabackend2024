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
        try {
            response = processarTransacao(id, request);
        }catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity.badRequest().build();
        }


        return ResponseEntity.ok(response);
    }

    public TransacaoResponse processarTransacao(int id, TransacaoRequest request) throws Exception {
        String operacao = request.getTipo();
        if(!operacao.equals("c") && !operacao.equals("d")) {
            return null;
        }

        User user = repository.getUserById(id);

        return null;
    }
}
