package com.execoes;

public class RegistroJaExistenteException extends RuntimeException {
    public String getMessage() {
        return "O registro já se encontra disponível na tabela.";
    }
}
