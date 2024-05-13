package com.turismo;

public class Destino {
    private String nome;
    private Endereco endereco;

    public Destino(String nome, Endereco endereco) {
        this.nome = nome;
        this.endereco = endereco;
    }

    // Getters and Setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    // Fim dos Getters and Setters
}
