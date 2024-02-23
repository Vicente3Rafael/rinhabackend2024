package com.rinhabackend.repository;

import com.rinhabackend.entity.Transacao;
import com.rinhabackend.entity.User;
import com.rinhabackend.mapper.TransacaoRowMapper;
import com.rinhabackend.mapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;

@Repository
public class TestRepository {

    @Autowired private JdbcTemplate jdbcTemplate;

    public User getUserById(int id) {
        String sql = "select * from USERS where id = ?";

        List<User> users = jdbcTemplate.query(sql, new UserRowMapper(), id);

        return users.isEmpty() ? null: users.get(0);
    }

    public void updateUser(User user) {
        String sql = "update users set saldo_inicial = ? where id = ?";

        jdbcTemplate.update(sql, new Object[]{user.getSaldoInicial(), user.getId()}, new int[]{Types.BIGINT, Types.BIGINT});
    }

    public void saveTransacao(Transacao transacao, int userId) {
        String sql = "insert into transacoes values (?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, new Object[]{transacao.getValor(), transacao.getTipo(), transacao.getDescricao(), transacao.getDate(), userId},
                new int[]{Types.BIGINT, Types.VARCHAR, Types.VARCHAR, Types.DATE, Types.BIGINT});
    }

    public List<Transacao> getLastTenTransacoesBy(int userId) {
        String sql = "select * from (select * from transacoes where user_id = ? order by date desc) where rownum <= 10";

        return jdbcTemplate.query(sql, new TransacaoRowMapper(), userId);
    }
}
