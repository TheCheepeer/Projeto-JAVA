package com.turismo;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.execoes.CepInvalidoException;
import com.execoes.NameNotNullOrInvalidException;
import com.execoes.SemConexaoInternetException;
import com.github.gilbertotorrezan.viacep.se.ViaCEPClient;
import com.github.gilbertotorrezan.viacep.shared.ViaCEPEndereco;

public class Endereco {
    private String cep, logradouro, bairro, cidade, uf;

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    private int idEndereco, numero;

    public Endereco(int idEndereco, String cep, String logradouro, int numero, String bairro,
            String cidade,
            String uf) {
        this.idEndereco = idEndereco;
        this.cep = cep;
        this.logradouro = logradouro;
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

    public Boolean verificarCep() {
        String cepSemPontuacao = cep.replaceAll("[^0-9]", "");
        if (cepSemPontuacao.length() == 8) {
            return true;
        } else {
            return false;
        }
    }

    public String cepF(String cep) throws CepInvalidoException {
        if (verificarCep() == true) {
            String cepSemPontuacao = cep.replaceAll("[^0-9]", "");
            cep = cepSemPontuacao.substring(0, 5) + "-" + cep.substring(5, 8);
            return cep;
        } else {
            throw new CepInvalidoException();
        }
    }

    public void consultarCEP() throws SemConexaoInternetException, CepInvalidoException {
        String cepSemPontuacao = cep.replaceAll("[^0-9]", "");

        try {
            ViaCEPClient client = new ViaCEPClient();
            ViaCEPEndereco endereco = client.getEndereco(cepSemPontuacao);
            this.logradouro = endereco.getLogradouro();
            this.bairro = endereco.getBairro();
            this.cidade = endereco.getLocalidade();
            this.uf = endereco.getUf();
        } catch (IOException e) {
            throw new SemConexaoInternetException();
        } catch (NullPointerException e) {
            throw new CepInvalidoException();
        }
    }

    public String nameNotNull(String input) throws NameNotNullOrInvalidException {
        if (input == null || input.trim().isEmpty()) {
            throw new NameNotNullOrInvalidException();
        }
        String lettersAndSpacesPattern = "^[a-zA-Zà-úÀ-ÚâêîôûãõáéíóúçüÁÉÍÓÚÇÜ0-9 ]+$";
        Pattern pattern = Pattern.compile(lettersAndSpacesPattern);
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            return input;
        } else {
            throw new NameNotNullOrInvalidException();
        }
    }

    public String imprimirConsulta() {
        return "\nResultados do CEP:\n" + "\nlogradouro: " + getLogradouro() + "\nBairro: "
                + getBairro() + "\nCidade: " + getCidade() + "\nUf: " + getUf()
                + "\n\nOs dados conferem?\n\n1. Sim\n2. Não\n3. Colocar CEP novamente.\n";
    }

    public String toString() {
        return "IdEndereço: " + idEndereco + " CEP: " + cep + " Logradouro: " + logradouro + " Número: "
                + numero + " Bairro: " + bairro + " Cidade: "
                + cidade + " Uf: " + uf;
    }
}
