package com.turismo;

public class Destino {
    private int idDestino;

    private String nome;
    private int idEndereco;

    public Destino(int idDestino, String nome, int idEndereco) {
        this.idDestino = idDestino;
        this.nome = nome;
        this.idEndereco = idEndereco;
    }

    // Getters and Setters

    public int getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }

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

    // Fim dos Getters and Setters

    public String toString() {
        return "IdDestino: " + idDestino + "\tIdEndereco: " + "\tNome: " + nome;
    }
}
