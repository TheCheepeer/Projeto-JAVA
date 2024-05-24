package com.execoes;

public class TelefoneInvalidoExeception extends RuntimeException {
    @Override
    public String getMessage() {
        return "Telefone Inválido. \nVerifique e tente novamente";
    }
}
