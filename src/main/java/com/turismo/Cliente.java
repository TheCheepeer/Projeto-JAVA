package com.turismo;

import java.time.LocalDate;

public class Cliente extends Pessoa {
    private int idCliente;
    private String email;
    private Endereco endereco;
    private Passeio passeio;
    private Pagamento pagamento;

    // Getters and Setters

    public Cliente(String nome, String cpf, String telefone, LocalDate dataNascimento, int idCliente, String email,
            Endereco endereco,
            Passeio passeio, Pagamento pagamento) {
        super(nome, cpf, telefone, dataNascimento);
        this.idCliente = idCliente;
        this.email = email;
        this.endereco = endereco;
        this.passeio = passeio;
        this.pagamento = pagamento;
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

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    // Fim dos Getters and Setters

    public String toString() {
        return "Nome: " + getNome() + "\nId: " + idCliente + "\nCPF: " + imprimeCPF(getCpf())
                + "\nData de Nascimento: " + dataNascimentoFormatada() + "\nTelefone: "
                + imprimirTelefone(getTelefone()) + "\nEmail: " + email + "\nEndereco: " + endereco +
                "\nPasseio: " + passeio + "\nAcompanhantes: " + "\nPagamento: "
                + pagamento;
    }

}
