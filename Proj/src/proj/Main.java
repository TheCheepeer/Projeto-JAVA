package proj;

import java.util.Date;

public class Main {

    public static void main(String[] args) {

        Date dataN = new Date(2010, 5, 17);
        Dependentes jorge = new Dependentes(null, null, null, null, null);

        jorge.setDataNascimento(dataN);

        System.out.println("Data de Nascimento: " + jorge.dataNacimentoFormatada());
        System.out.println("É maior de idade? " + (jorge.verificarMaioridade() ? "Sim" : "Não"));
    }

}
