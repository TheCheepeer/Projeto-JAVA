package com.tests;

import com.turismo.Endereco;

public class testdate {
    public static void main(String[] args) {
        Endereco endereco = new Endereco(0, null, null, 0, null, null, null, null);
        String cep = "12345678";
        endereco.setCep(cep);
        endereco.consultarCEP();
    }
}
