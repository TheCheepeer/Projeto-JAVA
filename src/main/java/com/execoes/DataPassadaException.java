package com.execoes;

public class DataPassadaException extends RuntimeException {
    @Override
    public String getMessage() {
        return "A data fornecida está no passado. \nVerifique e tente novamente.";
    }
}
