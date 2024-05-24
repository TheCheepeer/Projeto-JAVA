package com.banco;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;

import com.turismo.Cliente;

public class ClienteDao {

    private Connection con;

    public ClienteDao() {
        con = Database.getInstance().getConnection();
    }

    public void inserir(Cliente cliente) throws SQLException {
        Statement stat = con.createStatement();
        stat.executeUpdate("insert into cliente values(" + cliente.getCpf() + ", '" + cliente.getNome() + ", '"
                + cliente.getEmail() + ", '" +
                ", '" + cliente.getDataNascimento() + ", '" + cliente.getPagamento().getIdPagamento() + ", '"
                + cliente.getEndereco().getIdEndereco() + ", '" + cliente.getPasseio().getIdPasseio() + "')");
        stat.close();
    }

}
