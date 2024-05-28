package com.turismo;

import java.time.LocalDate;

public class Cliente extends Pessoa {
    private int idCliente, idEndereco;
    private String email;

    public Cliente(String nome, String cpf, String telefone, LocalDate dataNascimento, int idCliente, String email,
            int idEndereco) {
        super(nome, cpf, telefone, dataNascimento);
        this.idCliente = idCliente;
        this.email = email;
        this.idEndereco = idEndereco;
    }

    // Getters and Setters

    public int getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
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
        return "Nome: " + getNome() + "\tId: " + idCliente + "\tCPF: " + imprimeCPF(getCpf())
                + "\tData de Nascimento: " + dataNascimentoFormatada() + "\tTelefone: "
                + imprimirTelefone(getTelefone()) + "\tEmail: " + email + "\tEndereco: " + idEndereco;
    }

}
