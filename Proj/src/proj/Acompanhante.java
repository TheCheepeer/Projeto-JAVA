package proj;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.InputMismatchException;

public class Acompanhante {
    private String nome, idAcompanhante = gerarIdAcompanhante(), cpf, telefone;
    private LocalDate dataNascimento;
    private Cliente responsavel;

    public Acompanhante(String nome, String cpf, String telefone, LocalDate dataNascimento,
            Cliente responsavel) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.responsavel = responsavel;
    }

    // Getters and setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Cliente getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Cliente responsavel) {
        this.responsavel = responsavel;
    }
    // Fim dos Getters and setters

    public String dataNascimentoFormatada() {
        DateTimeFormatter dataNascimentoF = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return dataNascimento.format(dataNascimentoF);

    }

    // Verifica a maioridade

    public boolean verificarMaioridade() {

        LocalDate dataAtual = LocalDate.now();

        Period periodo = Period.between(dataNascimento, dataAtual);

        if (periodo.getYears() >= 18) {
            return true;
        } else {
            return false;
        }
    }

    public String verificarMaioridadeFeedback() {
        if (verificarMaioridade() == true) {
            return "É maior de idade";
        } else {
            return "Não é maior de idade";
        }
    }

    // Gera Id do Acompanhante

    public String gerarIdAcompanhante() {
        Random random = new Random();
        Set<String> cadastros = new HashSet<>();

        do {
            int parte1 = 100 + random.nextInt(900);
            int parte2 = 10 + random.nextInt(10);
            int parte3 = 100 + random.nextInt(900);

            idAcompanhante = "A" + parte1 + "-" + parte2 + "-" + parte3;
        } while (!cadastros.add(idAcompanhante));

        return idAcompanhante;
    }

    // Verificar Cpf

    public static boolean VerificarCpf(String cpf) {
        // considera-se erro CPF"s formados por uma sequencia de numeros iguais

        if (cpf.equals("00000000000") ||
                cpf.equals("11111111111") ||
                cpf.equals("22222222222") || cpf.equals("33333333333") ||
                cpf.equals("44444444444") || cpf.equals("55555555555") ||
                cpf.equals("66666666666") || cpf.equals("77777777777") ||
                cpf.equals("88888888888") || cpf.equals("99999999999") ||
                (cpf.length() != 11))
            return (false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)

        try {
            // Primeiro digito verificador

            sm = 0;
            peso = 10;

            for (i = 0; i < 9; i++) {
                num = (int) (cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else
                dig10 = (char) (r + 48); // converte no respectivo caractere numerico

            // Segundo digito verificador
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else
                dig11 = (char) (r + 48);

            // Verifica se os digitos calculados conferem com os digitos informados.

            if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10)))
                return (true);
            else
                return (false);
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

    public static String imprimeCPF(String cpf) {
        if (VerificarCpf(cpf) == true) {
            return (cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." +
                    cpf.substring(6, 9) + "-" + cpf.substring(9, 11));
        } else {
            return "Cpf Inválido!";
        }
    }

    public String imprimirTelefone(String telefone) {
        if (telefone.length() != 11) {
            return "Telefone Inválido";
        } else {
            return ("(" + telefone.substring(0, 2) + ")" + telefone.substring(2, 7) + "-" +
                    telefone.substring(7, 11));
        }
    }

    public String toString() {
        return "Nome: " + nome + "\nId: " + idAcompanhante + "\nCPF: " + imprimeCPF(cpf) + "\nData de Nascimento: "
                + dataNascimentoFormatada() + "\nTelefone: " + imprimirTelefone(telefone) + "\nResponsável: "
                + responsavel
                + "\nÉ de maior? " + verificarMaioridadeFeedback();
    }

}
