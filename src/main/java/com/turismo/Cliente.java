package com.turismo;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Cliente extends Pessoa {
    private String idCliente, email;
    private Endereco endereco;
    private Passeio passeio;
    private Acompanhante acompanhantes;
    private Pagamento pagamento;

    // Getters and Setters

    public Cliente(String nome, String cpf, String telefone, LocalDate dataNascimento, String email, Endereco endereco,
            Passeio passeio, Acompanhante acompanhantes, Pagamento pagamento) {
        super(nome, cpf, telefone, dataNascimento);
        this.email = email;
        this.endereco = endereco;
        this.passeio = passeio;
        this.acompanhantes = acompanhantes;
        this.pagamento = pagamento;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Passeio getPasseio() {
        return passeio;
    }

    public void setPasseio(Passeio passeio) {
        this.passeio = passeio;
    }

    public Acompanhante getAcompanhantes() {
        return acompanhantes;
    }

    public void setAcompanhantes(Acompanhante acompanhantes) {
        this.acompanhantes = acompanhantes;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    // Fim dos Getters and Setters

    // Gera Id do cliente

    public String gerarIdCliente() {
        Random random = new Random();
        Set<String> cadastros = new HashSet<>();

        do {
            int parte1 = 100 + random.nextInt(900);
            int parte2 = 10 + random.nextInt(10);
            int parte3 = 100 + random.nextInt(900);

            idCliente = "C" + parte1 + "-" + parte2 + "-" + parte3;
        } while (!cadastros.add(idCliente));

        return idCliente;
    }

    public String toString() {
        return "Nome: " + getNome() + "\nId: " + idCliente + "\nCPF: " + imprimeCPF(getCpf())
                + "\nData de Nascimento: " + dataNascimentoFormatada() + "\nTelefone: "
                + imprimirTelefone(getTelefone()) + "\nEmail: " + email + "\nEndereco: " + endereco +
                "\nPasseio: " + passeio + "\nAcompanhantes: " + acompanhantes.getIdAcompanhante() + "\nPagamento: "
                + pagamento;
    }

}
