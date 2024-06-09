package com.turismo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.execoes.NameNotNullOrInvalidException;

public class Onibus {
    private String placa, modelo, empresa, tipoOnibus;

    private int idOnibus;

    public Onibus(int idOnibus, String placa, String modelo, String empresa, String tipoOnibus) {
        this.idOnibus = idOnibus;
        this.placa = placa;
        this.modelo = modelo;
        this.empresa = empresa;
        this.tipoOnibus = tipoOnibus;
    }

    // Getters and Setters

    public int getIdOnibus() {
        return idOnibus;
    }

    public void setIdOnibus(int idOnibus) {
        this.idOnibus = idOnibus;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getTipoOnibus() {
        return tipoOnibus;
    }

    public void setTipoOnibus(String tipoOnibus) {
        this.tipoOnibus = tipoOnibus;
    }

    // Fim dos Getters and Setters

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

    public String toString() {
        return "idOnibus: " + idOnibus + " Placa: " + placa + " Modelo: " + modelo + " Tipo de Onibus: " + tipoOnibus
                + " Empresa: " + empresa;
    }
}
