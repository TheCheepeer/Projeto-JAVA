package com.tests;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import com.banco.Database;
import com.turismo.Cliente;

public class testcopy {
    public static void main(String[] args) {
        Connection con = null;
        Cliente cliente = new Cliente(null, null, null, null, 0, null, 0);
        cliente.setNome("Lusca");
        cliente.setCpf("14319083717");
        cliente.setDataNascimento(LocalDate.of(2004, 11, 6));
        cliente.setTelefone("21968824470");
        cliente.setEmail("algo@gmail.com");
        cliente.setIdEndereco(0);

        try {
            con = Database.getInstance().getConnection();

            Statement statement = con.createStatement();
            statement.setQueryTimeout(30); // set timeout to 30 sec.

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
