package com.turismo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.banco.ClienteDao;
import com.banco.Database;

public class Main {

        public static void main(String[] args) throws Exception {
                Connection con = null;

                try {
                        con = Database.getInstance().getConnection();

                        Statement statement = con.createStatement();
                        statement.setQueryTimeout(30); // set timeout to 30 sec.

                        ClienteDao clienteDao = new ClienteDao();
                        clienteDao.delete(2);

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
