package com.execoes;

public class NameNotNullOrInvalidException extends RuntimeException {
    public String getMessage() {
        return "Nome vazio ou inválido.";
    }
}
