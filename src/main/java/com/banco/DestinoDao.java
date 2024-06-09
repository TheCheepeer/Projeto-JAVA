package com.banco;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.execoes.RegistroJaExistenteException;
import com.turismo.Destino;

public class DestinoDao {
    private Connection con;

    public DestinoDao() {
        con = Database.getInstance().getConnection();
    }

    public void inserir(Destino destino) throws SQLException {
        Statement stat = con.createStatement();
        String query = "INSERT INTO destino (nome, idEndereco) VALUES ('"
                +
                destino.getNome() + "', '" +
                destino.getIdEndereco() + "')";
        stat.executeUpdate(query);
        stat.close();
    }

    public List<Destino> getAll() throws SQLException {
        List<Destino> destinos = new ArrayList<Destino>();
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery("select * from destino");

        while (rs.next()) {
            // read the result set
            Destino destino = new Destino(0, null, 0);
            destino.setIdDestino(rs.getInt("IdDestino"));
            destino.setIdEndereco(rs.getInt("idEndereco"));
            destino.setNome(rs.getString("nome"));

            destinos.add(destino);
        }
        return destinos;
    }

    public Destino getById(int idDestino) throws SQLException {
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery("select * from destino where idDestino = " + idDestino);

        if (rs.next()) {
            Destino destino = new Destino(0, null, 0);
            destino.setIdDestino(rs.getInt("IdDestino"));
            destino.setIdEndereco(rs.getInt("idEndereco"));
            destino.setNome(rs.getString("nome"));
            return destino;
        }
        stat.close();
        return null;
    }

    public Destino getByName(String nome) throws SQLException {
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery("SELECT * FROM destino WHERE nome = '" + nome + "'");

        if (rs.next()) {
            Destino destino = new Destino(0, null, 0);
            destino.setIdDestino(rs.getInt("IdDestino"));
            destino.setIdEndereco(rs.getInt("idEndereco"));
            destino.setNome(rs.getString("nome"));
            return destino;
        }
        stat.close();
        return null;
    }

    public Destino getLast() throws SQLException {
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery("SELECT * FROM destino ORDER BY idDestino DESC LIMIT 1");

        if (rs.next()) {
            Destino destino = new Destino(0, null, 0);
            destino.setIdDestino(rs.getInt("IdDestino"));
            destino.setIdEndereco(rs.getInt("idEndereco"));
            destino.setNome(rs.getString("nome"));
            return destino;
        } else {
            throw new NullPointerException();
        }
    }

    public void verificarExistencia(String nome) throws SQLException {
        if (getByName(nome) != null || nome.isEmpty()) {
            throw new RegistroJaExistenteException();
        } else {
            return;
        }
    }

}
