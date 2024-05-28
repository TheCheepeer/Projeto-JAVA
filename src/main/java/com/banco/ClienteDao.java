package com.banco;

import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.turismo.Cliente;

public class ClienteDao {

    private Connection con;

    public ClienteDao() {
        con = Database.getInstance().getConnection();
    }

    public void inserir(Cliente cliente) throws SQLException {
        Statement stat = con.createStatement();
        String query = "INSERT INTO cliente (cpf, nome, email, telefone, dataNascimento, idendereco) VALUES ('"
                +
                cliente.getCpf() + "', '" +
                cliente.getNome() + "', '" +
                cliente.getEmail() + "', '" +
                cliente.imprimirTelefone(cliente.getTelefone()) + "', '" +
                cliente.dataNascimentoFormatada() + "', '" +
                cliente.getIdEndereco() + "')";
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
            Cliente cliente = new Cliente(null, null, null, null, 0, null, 0);
            cliente.setIdCliente(rs.getInt("IdCliente"));
            int idCliente = cliente.getIdCliente();
            cliente.setCpf(rs.getString("cpf"));
            cliente.setNome(rs.getString("nome"));
            cliente.setDataNascimento(toLocalDate(idCliente));
            cliente.setEmail(rs.getString("email"));
            cliente.setTelefone(rs.getString("telefone"));
            cliente.setIdEndereco(rs.getInt("idEndereco"));
            clientes.add(cliente);
        }
        return clientes;
    }

    public Cliente getById(int idCliente) throws SQLException {
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery("select * from cliente where idCliente = " + idCliente);

        if (rs.next()) {
            Cliente cliente = new Cliente(null, null, null, null, idCliente, null, idCliente);
            cliente.setIdCliente(rs.getInt("IdCliente"));
            cliente.setCpf(rs.getString("cpf"));
            cliente.setNome(rs.getString("nome"));
            cliente.setDataNascimento(toLocalDate(idCliente));
            cliente.setEmail(rs.getString("email"));
            cliente.setTelefone(rs.getString("telefone"));
            cliente.setIdEndereco(rs.getInt("idEndereco"));
            return cliente;
        }
        stat.close();
        return null;
    }

    public Cliente getByCpf(String cpf) throws SQLException {
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery("select * from cliente where cpf = " + cpf);

        if (rs.next()) {
            Cliente cliente = new Cliente(cpf, cpf, cpf, null, 0, cpf, 0);
            cliente.setIdCliente(rs.getInt("IdCliente"));
            int idCliente = cliente.getIdCliente();
            cliente.setCpf(rs.getString("cpf"));
            cliente.setNome(rs.getString("nome"));
            cliente.setDataNascimento(toLocalDate(idCliente));
            cliente.setEmail(rs.getString("email"));
            cliente.setTelefone(rs.getString("telefone"));
            cliente.setIdEndereco(rs.getInt("idEndereco"));
            return cliente;
        }
        stat.close();
        return null;
    }

    public void updateEmail(Cliente c, int idCliente) throws SQLException {
        Statement stat = con.createStatement();
        stat.executeUpdate("update cliente set email = " + c.getEmail() + " where idCliente = " + idCliente);
        stat.close();
    }

    public LocalDate toLocalDate(int idCliente) throws SQLException {
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery("select * from cliente where idCliente = " + idCliente);
        String dataNascimentoString = rs.getString("dataNascimento");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataNascimento = LocalDate.parse(dataNascimentoString, formatter);
        stat.close();
        return dataNascimento;
    }
}
