package com.execoes;

public class CpfInvalidoExeception extends RuntimeException {
    @Override
    public String getMessage() {
        return "Cpf Inválido. \nVerifique e tente novamente.";
    }
}
