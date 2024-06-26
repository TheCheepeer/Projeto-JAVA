package com.banco;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.execoes.RegistroJaExistenteException;
import com.turismo.Onibus;

public class OnibusDao {

    private Connection con;

    public OnibusDao() {
        con = Database.getInstance().getConnection();
    }

    public void inserir(Onibus onibus) throws SQLException {
        Statement stat = con.createStatement();
        String query = "INSERT INTO onibus (placa, modelo, empresa, tipo) VALUES ('"
                +
                onibus.getPlaca() + "', '" +
                onibus.getModelo() + "', '" +
                onibus.getEmpresa() + "', '" +
                onibus.getTipoOnibus() + "')";
        stat.executeUpdate(query);
        stat.close();
    }

    public List<Onibus> getAll() throws SQLException {
        List<Onibus> onibusList = new ArrayList<Onibus>();
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery("select * from onibus");

        while (rs.next()) {
            // read the result set
            Onibus onibus = new Onibus(0, null, null, null, null);
            onibus.setIdOnibus(rs.getInt("IdOnibus"));
            onibus.setPlaca(rs.getString("placa"));
            onibus.setModelo(rs.getString("modelo"));
            onibus.setEmpresa(rs.getString("empresa"));
            onibus.setTipoOnibus("tipo");

            onibusList.add(onibus);
        }
        return onibusList;
    }

    public Onibus getById(int idOnibus) throws SQLException {
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery("select * from onibus where idOnibus = " + idOnibus);

        if (rs.next()) {
            Onibus onibus = new Onibus(0, null, null, null, null);
            onibus.setIdOnibus(rs.getInt("IdOnibus"));
            onibus.setPlaca(rs.getString("placa"));
            onibus.setModelo(rs.getString("modelo"));
            onibus.setEmpresa(rs.getString("empresa"));
            onibus.setTipoOnibus("tipo");
            return onibus;
        }
        stat.close();
        return null;
    }

    public Onibus getByPlaca(String placa) throws SQLException {
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery("SELECT * FROM onibus WHERE placa = '" + placa + "'");

        if (rs.next()) {
            Onibus onibus = new Onibus(0, null, null, null, null);
            onibus.setIdOnibus(rs.getInt("IdOnibus"));
            onibus.setPlaca(rs.getString("placa"));
            onibus.setModelo(rs.getString("modelo"));
            onibus.setEmpresa(rs.getString("empresa"));
            onibus.setTipoOnibus("tipo");
            return onibus;
        }
        stat.close();
        return null;
    }

    public Onibus getLast() throws SQLException {
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery("SELECT * FROM onibus ORDER BY idOnibus DESC LIMIT 1");

        if (rs.next()) {
            Onibus onibus = new Onibus(0, null, null, null, null);
            onibus.setIdOnibus(rs.getInt("IdOnibus"));
            onibus.setPlaca(rs.getString("placa"));
            onibus.setModelo(rs.getString("modelo"));
            onibus.setEmpresa(rs.getString("empresa"));
            onibus.setTipoOnibus("tipo");
            return onibus;
        } else {
            throw new NullPointerException();
        }
    }

    public void verificarExistencia(String placa) throws SQLException {
        if (getByPlaca(placa) != null || placa.isEmpty()) {
            throw new RegistroJaExistenteException();
        } else {
            return;
        }
    }
}
