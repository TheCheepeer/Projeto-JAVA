package com.execoes;

public class EmailInvalidoException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Email Inválido.\nVerifique e tente novamente.";
    }
}
