package com.turismo;

import java.io.IOException;

import com.execoes.CepInvalidoExeception;
import com.execoes.SemConexaoInternetExeception;
import com.github.gilbertotorrezan.viacep.se.ViaCEPClient;
import com.github.gilbertotorrezan.viacep.shared.ViaCEPEndereco;

public class Endereco {
    private String cep, logradouro, complemento, bairro, cidade, uf;

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    private int idEndereco, numero;

    public Endereco(int idEndereco, String cep, String logradouro, int numero, String complemento, String bairro,
            String cidade,
            String uf) {
        this.idEndereco = idEndereco;
        this.cep = cep;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.bairro = bairro;
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
            complemento = "Sem complemento";
            return complemento;
        } else {
            return complemento;
        }
    }

    public Boolean verificarCep() {
        String cepSemPontuacao = cep.replaceAll("[^0-9]", "");
        if (cepSemPontuacao.length() == 8) {
            return true;
        } else {
            return false;
        }
    }

    public String cepF(String cep) throws CepInvalidoExeception {
        if (verificarCep() == true) {
            cep = cep.substring(0, 5) + "-" + cep.substring(5, 8);
            return cep;
        } else {
            throw new CepInvalidoExeception();
        }
    }

    public void consultarCEP() throws SemConexaoInternetExeception {
        verificarCep();
        String cepSemPontuacao = cep.replaceAll("[^0-9]", "");

        try {
            ViaCEPClient client = new ViaCEPClient();
            ViaCEPEndereco endereco = client.getEndereco(cepSemPontuacao);
            this.logradouro = endereco.getLogradouro();
            this.bairro = endereco.getBairro();
            this.cidade = endereco.getLocalidade();
            this.uf = endereco.getUf();
        } catch (IOException e) {
            throw new SemConexaoInternetExeception();
        }
    }

    public String toString() {
        return "IdEndereço: " + idEndereco + "\tCEP: " + cep + "\tLogradouro: " + logradouro + "\tNúmero: "
                + numero + "\tComplemento: " + fixComplemento(complemento) + "\tBairro: " + bairro + "\tCidade: "
                + cidade + "\tUf: " + uf;
    }
}
