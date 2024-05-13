package com.turismo;

public class Onibus {
    private String placa, modelo, empresa, tipoOnibus;

    public Onibus(String placa, String modelo, String empresa, String tipoOnibus) {
        this.placa = placa;
        this.modelo = modelo;
        this.empresa = empresa;
        this.tipoOnibus = tipoOnibus;
    }

    // Getters and Setters

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
}
