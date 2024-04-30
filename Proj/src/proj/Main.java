package proj;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        Acompanhante primeiro = new Acompanhante("Lucas", "14319083717", "21968824470", LocalDate.of(2004, 11, 6),
                null);
        System.out.println(primeiro.toString());

    }

}
