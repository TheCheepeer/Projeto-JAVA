package com.tests;

import java.sql.Connection;

import com.banco.ClienteDao;
import com.banco.Database;
import com.turismo.Cliente;

public class testdate {
    public static void main(String[] args) {
        Connection con = null;
        ClienteDao clienteDao = new ClienteDao();
        Cliente cliente = new Cliente(null, null, null, null, 0, null, 0);
        try {
            con = Database.getInstance().getConnection();
            cliente = clienteDao.getByCpf("143.190.837-17");
            System.out.println(cliente);

        } catch (Exception e) {
            System.err.println("algo errado");
        }
    }
}
