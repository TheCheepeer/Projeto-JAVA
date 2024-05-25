package com.turismo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.JOptionPane;

import com.banco.ClienteDao;
import com.banco.Database;

public class Main {

        public static void main(String[] args) throws Exception {
                Connection con = null;

                // Passeio
                Passeio passeio = new Passeio(0, 0, 0, 0, 0, null, null, 0);
                passeio.setIdCliente(0);
                passeio.setIdOnibus(0);
                passeio.setIdDestino(0);
                passeio.setIdPagamento(0);
                passeio.setData(LocalDate.of(2024, 8, 19));
                passeio.setHora(LocalTime.of(12, 30, 0));
                passeio.setPreco(200);
                System.out.println(passeio.toString());

                try {
                        con = Database.getInstance().getConnection();

                        Statement statement = con.createStatement();
                        statement.setQueryTimeout(30); // set timeout to 30 sec.

                        // ClienteDao clienteDao = new ClienteDao();
                        // clienteDao.delete(2);

                        JOptionPane.showMessageDialog(null, "TUDO PRONTO!!");
                } catch (SQLException e) {
                        System.err.println(e.getMessage());
                        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                } finally {
                        try {
                                if (con != null) {
                                        con.close();
                                }
                        } catch (SQLException e) {
                                System.err.println(e);
                        }
                }
        }
}
