package com.turismo;

import java.time.LocalDate;

public class Cliente extends Pessoa {
    private int idCliente, idEndereco, idPasseio, idPagamento;
    private String email;
    private Endereco endereco;
    private Passeio passeio;
    private Pagamento pagamento;

    public Cliente(String nome, String cpf, String telefone, LocalDate dataNascimento, int idCliente, String email,
            int idEndereco,
            int idPasseio, int idPagamento) {
        super(nome, cpf, telefone, dataNascimento);
        this.idCliente = idCliente;
        this.email = email;
        this.idEndereco = idEndereco;
        this.idPasseio = idPasseio;
        this.idPagamento = idPagamento;
    }

    // Getters and Setters

    public int getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }

    public int getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(int idPagamento) {
        this.idPagamento = idPagamento;
    }

    public int getIdPasseio() {
        return idPasseio;
    }

    public void setIdPasseio(int idPasseio) {
        this.idPasseio = idPasseio;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Fim dos Getters and Setters

    public String toString() {
        return "Nome: " + getNome() + "\nId: " + idCliente + "\nCPF: " + imprimeCPF(getCpf())
                + "\nData de Nascimento: " + dataNascimentoFormatada() + "\nTelefone: "
                + imprimirTelefone(getTelefone()) + "\nEmail: " + email + "\nEndereco: " + idEndereco +
                "\nPasseio: " + idPasseio + "\nAcompanhantes: " + "\nPagamento: "
                + idPagamento;
    }

}
