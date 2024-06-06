package com.turismo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.execoes.NameNotNullOrInvalidException;

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

    public String nomeValido(String input) throws NameNotNullOrInvalidException {
        if (input == null || input.trim().isEmpty()) {
            throw new NameNotNullOrInvalidException();
        }
        String lettersAndSpacesPattern = "^[a-zA-Z ]+$";
        Pattern pattern = Pattern.compile(lettersAndSpacesPattern);
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            return input;
        } else {
            throw new NameNotNullOrInvalidException();
        }
    }

    public String toString() {
        return "IdDestino: " + idDestino + " IdEndereco: " + idEndereco + " Nome: " + nome;
    }
}
