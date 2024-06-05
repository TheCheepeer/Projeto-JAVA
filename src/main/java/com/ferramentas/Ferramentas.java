package com.ferramentas;

import java.io.IOException;

public class Ferramentas {

    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException e) {
            System.err.println("Erro de entrada/saída ao tentar limpar o console.");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.err.println("O thread foi interrompido ao tentar limpar o console.");
            e.printStackTrace();
        } catch (SecurityException e) {
            System.err.println("Permissão negada para limpar o console.");
            e.printStackTrace();
        }
    }
}
