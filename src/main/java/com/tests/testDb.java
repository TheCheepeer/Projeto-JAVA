package com.tests;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.io.IOException;

import javax.swing.JOptionPane;

import com.banco.ClienteDao;
import com.banco.Database;
import com.banco.PasseioDao;
import com.turismo.*;

public class testDb {
    public static void main(String[] args) throws IOException {
        Connection con = null;

        Cliente cliente = new Cliente(null, null, null, null, 0, null, 0, 0, 0);
        Passeio passeio = new Passeio(0, 0, 0, 0, 0, null, null, 0);
        Pagamento pagamento = new Pagamento(0, passeio, cliente, false);
        Endereco endereco = new Endereco(0, null, null, null, null, null, 0);
        Destino destino = new Destino(0, null, null);
        Onibus onibus = new Onibus(0, "ghegye73", "Forbes", "Palmares", "Anão");

        // Cliente
        cliente.setCpf("14319083717");
        cliente.setNome("Lucas");
        cliente.setDataNascimento(LocalDate.of(2004, 11, 6));
        cliente.setEmail("lucasribeiro@gmail.com");
        cliente.setTelefone("21968824470");
        System.out.println(cliente.getDataNascimento());

        // Endereço

        endereco.setCep("23580250");
        endereco.setLogradouro("Estrada da Paciência");
        endereco.setNumero(615);
        endereco.setComplemento("Qd C, Lt 19");
        endereco.setCidade("Rio de Janeiro");
        endereco.setUf("Rio de Janeiro");

        // Destino

        destino.setEndereco(endereco);
        destino.setNome("Paciência City");

        // Pagamento

        pagamento.setCliente(cliente);
        pagamento.setPagou(true);
        pagamento.setPasseio(passeio);

        // Passeio

        passeio.setIdCliente(0);
        passeio.setIdOnibus(0);
        passeio.setIdDestino(0);
        passeio.setIdPagamento(0);
        passeio.setData(LocalDate.of(2024, 8, 19));
        passeio.setHora(LocalTime.of(12, 30, 0));
        passeio.setPreco(200);

        try {
            con = Database.getInstance().getConnection();

            Statement statement = con.createStatement();
            statement.setQueryTimeout(30); // set timeout to 30 sec.

            ClienteDao clienteDao = new ClienteDao();
            List<Cliente> clientes = clienteDao.getAll();

            PasseioDao passeioDao = new PasseioDao();
            List<Passeio> passeios = passeioDao.getAll();

            // passeioDao.inserir(passeio);

            // clienteDao.inserir(cliente);
            // clienteDao.delete(1);

            for (Cliente c : clientes) {
                System.out.println(c);
            }

            for (Passeio p : passeios) {
                System.out.println(p);
            }

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
