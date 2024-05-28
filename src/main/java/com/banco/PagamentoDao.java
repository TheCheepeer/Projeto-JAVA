package com.banco;

import java.sql.Connection;
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
        String query = "INSERT INTO pagamento (idCliente, idPasseio, pagou) VALUES ('"
                +
                pagamento.getIdCliente() + "', '" +
                pagamento.getIdPasseio() + "', '" +
                pagamento.situacao() + "')";
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
            pagamento.setPagou(stringToBoolean(idPagamento));
            pagamentos.add(pagamento);
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
            pagamento.setPagou(stringToBoolean(idPagamento));
            return pagamento;
        }
        stat.close();
        return null;
    }

    public boolean stringToBoolean(int idPagamento) throws SQLException {
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery("select * from pagamento where idPagamento = " + idPagamento);
        String situacao = rs.getString("pagou");
        String controle = "Pagamento efetuado";
        if (situacao.equals(controle)) {
            return true;
        } else {
            return false;
        }
    }
}
