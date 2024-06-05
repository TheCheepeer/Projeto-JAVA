package com.ferramentas;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.banco.Database;
import com.banco.EnderecoDao;
import com.turismo.Endereco;

public class testdate {
    public static void main(String[] args) {
        Connection con = null;
        try {
            con = Database.getInstance().getConnection();

            Statement statement = con.createStatement();
            statement.setQueryTimeout(30); // set timeout to 30 sec.

            Endereco endereco = new Endereco(0, "23580-250", "Estrada da Paciência", 615,
                    "Paciência", "Rio de Janeiro", "RJ");
            EnderecoDao enderecoDao = new EnderecoDao();
            System.out.println(endereco + "\n");
            int idEndereco = enderecoDao.verificarExistenciaEObterId(endereco.getCep(), endereco.getLogradouro(),
                    endereco.getNumero(), endereco.getBairro(), endereco.getCidade(),
                    endereco.getUf());
            endereco = enderecoDao.getById(idEndereco);
            System.out.println(endereco);

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
