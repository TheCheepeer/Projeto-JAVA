package com.turismo;

public class Destino {
    private int idDestino;

    private String nome;
    private Endereco endereco;

    public Destino(int idDestino, String nome, Endereco endereco) {
        this.idDestino = idDestino;
        this.nome = nome;
        this.endereco = endereco;
    }

    // Getters and Setters

    public int getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(int idDestino) {
        this.idDestino = idDestino;
    }

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
