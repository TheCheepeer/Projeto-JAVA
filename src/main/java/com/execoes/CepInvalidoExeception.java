package com.execoes;

public class CepInvalidoExeception extends RuntimeException {
    @Override
    public String getMessage() {
        return "Cpf Inválido. \nVerifique e tente novamente.";
    }
}
