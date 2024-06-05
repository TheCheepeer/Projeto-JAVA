package com.ferramentas;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import java.io.IOException;

import javax.swing.JOptionPane;

import com.banco.Database;

public class testDb {
    public static void main(String[] args) throws IOException {
        Connection con = null;

        /*
         * Cliente cliente = new Cliente(null, null, null, null, 0, null, 0, 0, 0);
         * Passeio passeio = new Passeio(0, 0, 0, 0, 0, null, null, 0);
         * Pagamento pagamento = new Pagamento(0, 0, 0, false);
         * Endereco endereco = new Endereco(0, null, null, 0, null, null, null, null);
         * Destino destino = new Destino(0, null, 0);
         * Onibus onibus = new Onibus(0, "ghegye73", "Forbes", "Palmares", "Anão");
         * 
         * // Cliente
         * cliente.setCpf("14319083717");
         * cliente.setNome("Lucas");
         * cliente.setDataNascimento(LocalDate.of(2004, 11, 6));
         * cliente.setEmail("lucasribeiro@gmail.com");
         * cliente.setTelefone("21968824470");
         * System.out.println(cliente.getDataNascimento());
         * 
         * // Endereço
         * 
         * endereco.setCep("23580250");
         * endereco.setLogradouro("Estrada da Paciência");
         * endereco.setNumero(615);
         * endereco.setComplemento("Qd C, Lt 19");
         * endereco.setBairro("Paciência");
         * endereco.setCidade("Rio de Janeiro");
         * endereco.setUf("Rio de Janeiro");
         * 
         * // Destino
         * 
         * destino.setIdEndereco(0);
         * destino.setNome("Paciência City");
         * 
         * // Pagamento
         * 
         * pagamento.setIdPagamento(0);
         * pagamento.setIdCliente(0);
         * pagamento.setIdPasseio(0);
         * pagamento.setPagou(true);
         * 
         * // Passeio
         * 
         * passeio.setIdCliente(0);
         * passeio.setIdOnibus(0);
         * passeio.setIdDestino(0);
         * passeio.setIdPagamento(0);
         * passeio.setData(LocalDate.of(2024, 8, 19));
         * passeio.setHora(LocalTime.of(12, 30, 0));
         * passeio.setPreco(200);
         */

        try {
            con = Database.getInstance().getConnection();

            Statement statement = con.createStatement();
            statement.setQueryTimeout(30); // set timeout to 30 sec.

            // ClienteDao clienteDao = new ClienteDao();
            // List<Cliente> clientes = clienteDao.getAll();

            // PasseioDao passeioDao = new PasseioDao();
            // List<Passeio> passeios = passeioDao.getAll();

            // DestinoDao destinoDao = new DestinoDao();
            // List<Destino> destinos = destinoDao.getAll();

            // EnderecoDao enderecoDao = new EnderecoDao();
            // List<Endereco> enderecos = enderecoDao.getAll();

            // PagamentoDao pagamentoDao = new PagamentoDao();
            // List<Pagamento> pagamentos = pagamentoDao.getAll();

            // pagamentoDao.inserir(pagamento);

            // for (Pagamento p : pagamentos) {
            // System.out.println(p);
            // }

            // enderecoDao.inserir(endereco);

            // for (Endereco e : enderecos) {
            // System.out.println(e);
            // }

            // destinoDao.inserir(destino);

            // passeioDao.inserir(passeio);

            // clienteDao.inserir(cliente);
            // clienteDao.delete(1);

            // for (Cliente c : clientes) {
            // System.out.println(c);
            // }

            // for (Passeio p : passeios) {
            // System.out.println(p);
            // }

            // for (Destino d : destinos) {
            // System.out.println(d);
            // }

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
