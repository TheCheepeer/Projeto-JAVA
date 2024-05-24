package com.turismo;

public class Endereco {
    private String cep, logradouro, complemento, cidade, uf;
    private int idEndereco, numero;

    public Endereco(int idEndereco, String cep, String logradouro, String complemento, String cidade, String uf,
            int numero) {
        this.idEndereco = idEndereco;
        this.cep = cep;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.cidade = cidade;
        this.uf = uf;
        this.numero = numero;
    }

    // Getters and setters

    public int getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    // Fim dos Getters and setters

    public String fixComplemento(String complemento) {
        if (complemento == null) {
            complemento = "";
            return complemento;
        } else {
            return complemento + " - ";
        }
    }

    public String toString() {
        return logradouro + ", " + numero + " - " + fixComplemento(complemento) + cep + " " + cidade + "/" + uf;
    }
}
