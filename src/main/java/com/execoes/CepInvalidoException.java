package com.execoes;

public class CepInvalidoException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Cpf Inválido. \nVerifique e tente novamente.";
    }
}
