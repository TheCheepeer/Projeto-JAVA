package com.execoes;

public class CpfInvalidoException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Cpf Inválido. \nVerifique e tente novamente.";
    }
}
