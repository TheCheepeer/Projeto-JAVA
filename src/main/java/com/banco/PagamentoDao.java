package com.banco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.turismo.Pagamento;

public class PagamentoDao {
    private Connection con;

    public PagamentoDao() {
        con = Database.getInstance().getConnection();
    }

    public void inserir(Pagamento pagamento) throws SQLException {
        Statement stat = con.createStatement();
        String query = "INSERT INTO pagamento (idCliente, idPasseio, situacao) VALUES ('"
                +
                pagamento.getIdCliente() + "', '" +
                pagamento.getIdPasseio() + "', '" +
                pagamento.situacaoToString() + "')";
        stat.executeUpdate(query);
        stat.close();
    }

    public void delete(int idPagamento) throws SQLException {
        Statement stat = con.createStatement();
        stat.executeUpdate("delete from pagamento where idPagamento = " + idPagamento);
        stat.close();
    }

    public List<Pagamento> getAll() throws SQLException {
        List<Pagamento> pagamentos = new ArrayList<Pagamento>();
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery("select * from pagamento");

        while (rs.next()) {
            // read the result set
            Pagamento pagamento = new Pagamento(0, 0, 0, false);
            pagamento.setIdPagamento(rs.getInt("idPagamento"));
            int idPagamento = pagamento.getIdPagamento();
            pagamento.setIdCliente(rs.getInt("idCliente"));
            pagamento.setIdPasseio(rs.getInt("idPasseio"));
            pagamento.setSituacao(stringToBoolean(idPagamento));
            pagamentos.add(pagamento);
        }
        return pagamentos;
    }

    public List<Pagamento> getByCliente(int idCliente) throws SQLException {
        List<Pagamento> pagamentos = new ArrayList<>();
        String query = "SELECT * FROM pagamento WHERE idCliente = ?";
        try (PreparedStatement stat = con.prepareStatement(query)) {
            stat.setInt(1, idCliente);

            try (ResultSet rs = stat.executeQuery()) {
                while (rs.next()) {
                    Pagamento pagamento = new Pagamento(0, 0, 0, false);
                    pagamento.setIdPagamento(rs.getInt("idPagamento"));
                    pagamento.setIdCliente(rs.getInt("idCliente"));
                    pagamento.setIdPasseio(rs.getInt("idPasseio"));
                    pagamento.setSituacao(rs.getBoolean("situacao"));
                    pagamentos.add(pagamento);
                }
            }
        }
        return pagamentos;
    }

    public List<Pagamento> getByPasseio(int idPasseio) throws SQLException {
        List<Pagamento> pagamentos = new ArrayList<>();
        String query = "SELECT * FROM pagamento WHERE idPasseio = ?";
        try (PreparedStatement stat = con.prepareStatement(query)) {
            stat.setInt(1, idPasseio);

            try (ResultSet rs = stat.executeQuery()) {
                while (rs.next()) {
                    Pagamento pagamento = new Pagamento(0, 0, 0, false);
                    pagamento.setIdPagamento(rs.getInt("idPagamento"));
                    pagamento.setIdCliente(rs.getInt("idCliente"));
                    pagamento.setIdPasseio(rs.getInt("idPasseio"));
                    pagamento.setSituacao(rs.getBoolean("situacao"));
                    pagamentos.add(pagamento);
                }
            }
        }
        return pagamentos;
    }

    public Pagamento getById(int idPagamento) throws SQLException {
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery("select * from pagamento where idPagamento = " + idPagamento);

        if (rs.next()) {
            Pagamento pagamento = new Pagamento(0, 0, 0, false);
            pagamento.setIdPagamento(rs.getInt("idPagamento"));
            pagamento.setIdCliente(rs.getInt("idCliente"));
            pagamento.setIdPasseio(rs.getInt("idPasseio"));
            pagamento.setSituacao(stringToBoolean(idPagamento));
            return pagamento;
        }
        stat.close();
        return null;
    }

    public Pagamento getByIdCliente(int idCliente) throws SQLException {
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery("select * from pagamento where idCliente = " + idCliente);

        if (rs.next()) {
            Pagamento pagamento = new Pagamento(0, 0, 0, false);
            pagamento.setIdPagamento(rs.getInt("idPagamento"));
            int idPagamento = pagamento.getIdPagamento();
            pagamento.setIdCliente(rs.getInt("idCliente"));
            pagamento.setIdPasseio(rs.getInt("idPasseio"));
            pagamento.setSituacao(stringToBoolean(idPagamento));
            return pagamento;
        }
        stat.close();
        return null;
    }

    public boolean stringToBoolean(int idPagamento) throws SQLException {
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery("select * from pagamento where idPagamento = " + idPagamento);
        String situacao = rs.getString("situacao");
        String controle = "Pagamento efetuado";
        if (situacao.equals(controle)) {
            return true;
        } else {
            return false;
        }
    }

    public Pagamento getLast() throws SQLException, NullPointerException {
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery("SELECT * FROM pagamento ORDER BY idPagamento DESC LIMIT 1");

        if (rs.next()) {
            Pagamento pagamento = new Pagamento(0, 0, 0, false);
            pagamento.setIdPagamento(rs.getInt("idPagamento"));
            int idPagamento = pagamento.getIdPagamento();
            pagamento.setIdCliente(rs.getInt("idCliente"));
            pagamento.setIdPasseio(rs.getInt("idPasseio"));
            pagamento.setSituacao(stringToBoolean(idPagamento));
            return pagamento;
        } else {
            throw new NullPointerException();
        }
    }

    public int verificarExistenciaEObterId(int idCliente, int idPasseio) throws SQLException {
        Statement stat = con.createStatement();
        String query = String.format(
                "SELECT idPagamento FROM pagamento WHERE idCliente = '%d' AND idPasseio = '%d'",
                idCliente, idPasseio);
        ResultSet rs = stat.executeQuery(query);

        if (rs.next()) {
            return rs.getInt("idPagamento");
        } else {
            return -1; // Endereço não encontrado
        }

    }

    public void updateSituacao(Pagamento p, int idPagamento) throws SQLException {
        Statement stat = con.createStatement();
        String query = "UPDATE pagamento SET situacao = '" + p.getSituacao() + "' WHERE idPagamento = "
                + idPagamento;
        stat.executeUpdate(query);
        stat.close();
    }

}
