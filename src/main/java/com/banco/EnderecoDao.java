package com.banco;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.execoes.RegistroJaExistenteException;
import com.turismo.Endereco;

public class EnderecoDao {
    private Connection con;

    public EnderecoDao() {
        con = Database.getInstance().getConnection();
    }

    public void inserir(Endereco endereco) throws SQLException {
        Statement stat = con.createStatement();
        String query = "INSERT INTO endereco (cep, logradouro, numero, complemento, bairro, cidade, uf) VALUES ('"
                +
                endereco.cepF(endereco.getCep()) + "', '" +
                endereco.getLogradouro() + "', '" +
                endereco.getNumero() + "', '" +
                endereco.getComplemento() + "', '" +
                endereco.getBairro() + "', '" +
                endereco.getCidade() + "', '" +
                endereco.getUf() + "')";
        stat.executeUpdate(query);
        stat.close();
    }

    public void delete(int idEndereco) throws SQLException {
        Statement stat = con.createStatement();
        stat.executeUpdate("delete from endereco where idEndereco = " + idEndereco);
        stat.close();
    }

    public List<Endereco> getAll() throws SQLException {
        List<Endereco> enderecos = new ArrayList<Endereco>();
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery("select * from endereco");

        while (rs.next()) {
            // read the result set
            Endereco endereco = new Endereco(0, null, null, 0, null, null, null, null);
            endereco.setIdEndereco(rs.getInt("idEndereco"));
            endereco.setCep(rs.getString("cep"));
            endereco.setLogradouro(rs.getString("logradouro"));
            endereco.setNumero(rs.getInt("numero"));
            endereco.setComplemento(rs.getString("complemento"));
            endereco.setBairro(rs.getString("bairro"));
            endereco.setCidade(rs.getString("cidade"));
            endereco.setUf(rs.getString("uf"));
            enderecos.add(endereco);
        }
        return enderecos;
    }

    public Endereco getById(int idEndereco) throws SQLException {
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery("select * from endereco where idEndereco = " + idEndereco);

        if (rs.next()) {
            Endereco endereco = new Endereco(0, null, null, 0, null, null, null, null);
            endereco.setIdEndereco(rs.getInt("idEndereco"));
            endereco.setCep(rs.getString("cep"));
            endereco.setLogradouro(rs.getString("logradouro"));
            endereco.setNumero(rs.getInt("numero"));
            endereco.setComplemento(rs.getString("complemento"));
            endereco.setBairro(rs.getString("bairro"));
            endereco.setCidade(rs.getString("cidade"));
            endereco.setUf(rs.getString("uf"));
            return endereco;
        }
        stat.close();
        return null;
    }

    public Endereco getLast() throws SQLException {
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery("SELECT * FROM endereco ORDER BY idEndereco DESC LIMIT 1");

        if (rs.next()) {
            Endereco endereco = new Endereco(0, null, null, 0, null, null, null, null);
            endereco.setIdEndereco(rs.getInt("idEndereco"));
            endereco.setCep(rs.getString("cep"));
            endereco.setLogradouro(rs.getString("logradouro"));
            endereco.setNumero(rs.getInt("numero"));
            endereco.setComplemento(rs.getString("complemento"));
            endereco.setBairro(rs.getString("bairro"));
            endereco.setCidade(rs.getString("cidade"));
            endereco.setUf(rs.getString("uf"));
            return endereco;
        } else {
            throw new NullPointerException();
        }
    }

    public void verificarExistencia(String cep, String logradouro, int numero, String complemento, String bairro,
            String cidade,
            String uf) throws SQLException {
        Statement stat = con.createStatement();
        ResultSet rs = stat
                .executeQuery(
                        "SELECT idEndereco FROM endereco WHERE cep = '" + cep + "' AND logradouro = '" + logradouro +
                                "' AND numero = '" + numero + "' AND complemento = '" + complemento + "' AND bairro = '"
                                + bairro + "' AND cidade = '" +
                                cidade + "' AND uf = '" + uf + "'");

        if (rs.next()) {
            throw new RegistroJaExistenteException();
        }
    }

    public Endereco getOnInfo(String cep, String logradouro, int numero, String complemento, String bairro,
            String cidade,
            String uf) throws SQLException {
        Statement stat = con.createStatement();
        ResultSet rs = stat
                .executeQuery(
                        "SELECT idEndereco FROM endereco WHERE cep = '" + cep + "' AND logradouro = '" + logradouro +
                                "' AND numero = '" + numero + "' AND complemento = '" + complemento + "' AND bairro = '"
                                + bairro + "' AND cidade = '" +
                                cidade + "' AND uf = '" + uf + "'");

        if (rs.next()) {
            Endereco endereco = new Endereco(0, null, null, 0, null, null, null, null);
            endereco.setIdEndereco(rs.getInt("idEndereco"));
            endereco.setCep(rs.getString("cep"));
            endereco.setLogradouro(rs.getString("logradouro"));
            endereco.setNumero(rs.getInt("numero"));
            endereco.setComplemento(rs.getString("complemento"));
            endereco.setBairro(rs.getString("bairro"));
            endereco.setCidade(rs.getString("cidade"));
            endereco.setUf(rs.getString("uf"));
            return endereco;
        }
        stat.close();
        return null;

    }
}
