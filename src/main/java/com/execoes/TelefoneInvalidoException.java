package com.execoes;

public class TelefoneInvalidoException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Telefone Inválido. \nVerifique e tente novamente";
    }
}
