package com.execoes;

public class SemConexaoInternetException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Sem Conexão com a internet, insira manualmente.";
    }
}
