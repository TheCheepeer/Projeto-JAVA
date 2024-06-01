package com.tests;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.banco.ClienteDao;
import com.banco.Database;
import com.turismo.Cliente;

public class testdate {
    public static void main(String[] args) {
        Connection con = null;
        try {
            con = Database.getInstance().getConnection();

            Statement statement = con.createStatement();
            statement.setQueryTimeout(30); // set timeout to 30 sec.

            Cliente cliente = new Cliente(null, null, null, null, 0, null, 0);
            ClienteDao clienteDao = new ClienteDao();
            cliente = clienteDao.getByCpf(Cliente.unFormatCpf("143.190.837-17"));
            System.out.println(cliente);

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
