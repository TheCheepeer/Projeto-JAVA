package com.execoes;

public class ElementoNaoEncontradoExption extends RuntimeException {
    @Override
    public String getMessage() {
        return "Elemento não encontrado.\nVerifique e tente novamente.";
    }
}
