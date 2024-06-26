package com.banco;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
        String query = "INSERT INTO passeio (idDestino, idOnibus, data, hora, preco) VALUES ('"
                +
                passeio.getIdDestino() + "', '" +
                passeio.getIdOnibus() + "', '" +
                passeio.dataFormatada() + "', '" +
                passeio.horaF() + "', '" +
                passeio.getPreco() + "')";
        stat.executeUpdate(query);
        stat.close();
    }

    public void delete(int idPasseio) throws SQLException {
        Statement stat = con.createStatement();
        stat.executeUpdate("delete from passeio where idPasseio = " + idPasseio);
        stat.close();
    }

    public List<Passeio> getAll() throws SQLException {
        List<Passeio> passeios = new ArrayList<Passeio>();
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery("select * from passeio");

        while (rs.next()) {
            // read the result set
            Passeio passeio = new Passeio(0, 0, 0, 0, null, null);
            passeio.setIdPasseio(rs.getInt("IdPasseio"));
            int idPasseio = passeio.getIdPasseio();
            passeio.setIdDestino(rs.getInt("idDestino"));
            passeio.setIdOnibus(rs.getInt("idOnibus"));
            passeio.setData(toLocalDate(idPasseio));
            passeio.setHora(toLocalTime(idPasseio));
            passeio.setPreco(rs.getFloat("preco"));

            passeios.add(passeio);
        }
        return passeios;
    }

    public Passeio getById(int idPasseio) throws SQLException {
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery("select * from passeio where idPasseio = " + idPasseio);

        if (rs.next()) {
            Passeio passeio = new Passeio(0, 0, 0, 0, null, null);
            passeio.setIdPasseio(rs.getInt("IdPasseio"));
            passeio.setIdDestino(rs.getInt("idDestino"));
            passeio.setIdOnibus(rs.getInt("idOnibus"));
            passeio.setData(toLocalDate(idPasseio));
            passeio.setHora(toLocalTime(idPasseio));
            passeio.setPreco(rs.getFloat("preco"));
            return passeio;
        }
        stat.close();
        return null;
    }

    public Passeio getLast() throws SQLException {
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery("SELECT * FROM passeio ORDER BY idPasseio DESC LIMIT 1");

        if (rs.next()) {
            Passeio passeio = new Passeio(0, 0, 0, 0, null, null);
            passeio.setIdPasseio(rs.getInt("IdPasseio"));
            int idPasseio = passeio.getIdPasseio();
            passeio.setIdDestino(rs.getInt("idDestino"));
            passeio.setIdOnibus(rs.getInt("idOnibus"));
            passeio.setData(toLocalDate(idPasseio));
            passeio.setHora(toLocalTime(idPasseio));
            passeio.setPreco(rs.getFloat("preco"));
            return passeio;
        } else {
            throw new NullPointerException();
        }
    }

    public void updateData(Passeio p, int idPasseio) throws SQLException {
        Statement stat = con.createStatement();
        String query = "UPDATE passeio SET data = '" + p.dataFormatada() + "' WHERE idPasseio = " + idPasseio;
        stat.executeUpdate(query);
        stat.close();
    }

    public void updateHora(Passeio p, int idPasseio) throws SQLException {
        Statement stat = con.createStatement();
        String query = "UPDATE passeio SET hora = '" + p.horaF() + "' WHERE idPasseio = " + idPasseio;
        stat.executeUpdate(query);
        stat.close();
    }

    public void updateOnibus(Passeio p, int idPasseio) throws SQLException {
        Statement stat = con.createStatement();
        String query = "UPDATE passeio SET idOnibus = " + p.getIdOnibus() + " WHERE idPasseio = " + idPasseio;
        stat.executeUpdate(query);
        stat.close();
    }

    public LocalDate toLocalDate(int idPasseio) throws SQLException {
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery("select * from passeio where idPasseio = " + idPasseio);
        String dataString = rs.getString("data");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data = LocalDate.parse(dataString, formatter);
        stat.close();
        return data;
    }

    public LocalTime toLocalTime(int idPasseio) throws SQLException {
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery("select * from passeio where idPasseio = " + idPasseio);
        String horaString = rs.getString("hora");
        DateTimeFormatter horaF = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime hora = LocalTime.parse(horaString, horaF);
        stat.close();
        return hora;
    }

}
