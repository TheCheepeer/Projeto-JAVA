package com.execoes;

public class ConexaoInternetExeception extends RuntimeException {
    @Override
    public String getMessage() {
        return "Sem Conexão com a internet, insira manualmente.";
    }
}
