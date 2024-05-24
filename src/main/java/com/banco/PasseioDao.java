package com.banco;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.turismo.Passeio;

public class PasseioDao {
    private Connection con;

    public PasseioDao() {
        con = Database.getInstance().getConnection();
    }

    public void inserir(Passeio passeio) throws SQLException {
        Statement stat = con.createStatement();
        String query = "INSERT INTO passeio (cpf, nome, email, telefone, dataNascimento, idpagamento, idendereco, idpasseio) VALUES ('"
                +
                Cliente.imprimeCPF(cliente.getCpf()) + "', '" +
                cliente.getNome() + "', '" +
                cliente.getEmail() + "', '" +
                Cliente.imprimirTelefone(cliente.getTelefone()) + "', '" +
                cliente.dataNascimentoFormatada() + "', '" +
                cliente.getIdPagamento() + "', '" +
                cliente.getIdEndereco() + "', '" +
                cliente.getIdPasseio() + "')";
        stat.executeUpdate(query);
        stat.close();
    }

    public void delete(int idCliente) throws SQLException {
        Statement stat = con.createStatement();
        stat.executeUpdate("delete from cliente where idCliente = " + idCliente);
        stat.close();
    }

    public List<Cliente> getAll() throws SQLException {
        List<Cliente> clientes = new ArrayList<Cliente>();
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery("select * from cliente");

        while (rs.next()) {
            // read the result set
            Cliente cliente = new Cliente(null, null, null, null, 0, null, 0, 0, 0);
            cliente.setIdCliente(rs.getInt("IdCliente"));
            int idCliente = cliente.getIdCliente();
            cliente.setCpf(rs.getString("cpf"));
            cliente.setNome(rs.getString("nome"));
            cliente.setDataNascimento(toLocalDate(idCliente));
            cliente.setEmail(rs.getString("email"));
            cliente.setTelefone(rs.getString("telefone"));
            cliente.setIdEndereco(rs.getInt("idEndereco"));
            cliente.setIdPasseio(rs.getInt("idPasseio"));
            cliente.setIdPagamento(rs.getInt("idPagamento"));
            clientes.add(cliente);
        }
        return clientes;
    }

    public Cliente getById(int idCliente) throws SQLException {
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery("select * from cliente where idCliente = " + idCliente);

        if (rs.next()) {
            Cliente cliente = new Cliente(null, null, null, null, 0, null, 0, 0, 0);
            cliente.setIdCliente(rs.getInt("IdCliente"));
            cliente.setCpf(rs.getString("cpf"));
            cliente.setNome(rs.getString("nome"));
            cliente.setDataNascimento(toLocalDate(idCliente));
            cliente.setEmail(rs.getString("email"));
            cliente.setTelefone(rs.getString("telefone"));
            cliente.setIdEndereco(rs.getInt("idEndereco"));
            cliente.setIdPasseio(rs.getInt("idPasseio"));
            cliente.setIdPagamento(rs.getInt("idPagamento"));
            return cliente;
        }
        stat.close();
        return null;
    }

}
