package com.rinhabackend.mapper;

import com.rinhabackend.entity.Transacao;
import com.rinhabackend.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransacaoRowMapper implements RowMapper<Transacao> {

    @Override
    public Transacao mapRow(ResultSet rs, int rowNum) throws SQLException {
        Transacao transacao = new Transacao();

        transacao.setValor(rs.getInt(1));
        transacao.setTipo(rs.getString(2));
        transacao.setDescricao(rs.getString(3));
        transacao.setDate(rs.getDate(4));

        return transacao;
    }
 }
