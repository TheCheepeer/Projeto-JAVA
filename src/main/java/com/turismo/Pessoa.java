package com.turismo;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.execoes.CpfInvalidoException;
import com.execoes.NameNotNullOrInvalidException;
import com.execoes.TelefoneInvalidoException;

public class Pessoa {
    private String nome, cpf, telefone;
    private LocalDate dataNascimento;

    // Getters and setters

    public Pessoa(String nome, String cpf, String telefone, LocalDate dataNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
    }

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

    // Fim dos Getters and setters

    // Formata a data de nascimento

    public String dataNascimentoFormatada() {
        DateTimeFormatter dataNascimentoF = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dataNascimento.format(dataNascimentoF);
    }

    // Verificar Cpf

    public static boolean verificarCpf(String cpf) {
        // considera-se erro CPF"s formados por uma sequencia de numeros iguais

        String cpfSemPontuacao = cpf.replaceAll("[^0-9]", "");

        if (cpfSemPontuacao.equals("00000000000") ||
                cpfSemPontuacao.equals("11111111111") ||
                cpfSemPontuacao.equals("22222222222") || cpfSemPontuacao.equals("33333333333") ||
                cpfSemPontuacao.equals("44444444444") || cpfSemPontuacao.equals("55555555555") ||
                cpfSemPontuacao.equals("66666666666") || cpfSemPontuacao.equals("77777777777") ||
                cpfSemPontuacao.equals("88888888888") || cpfSemPontuacao.equals("99999999999") ||
                (cpfSemPontuacao.length() != 11))
            return (false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)

        try {
            // Primeiro digito verificador

            sm = 0;
            peso = 10;

            for (i = 0; i < 9; i++) {
                num = (int) (cpfSemPontuacao.charAt(i) - 48);
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
                num = (int) (cpfSemPontuacao.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else
                dig11 = (char) (r + 48);

            // Verifica se os digitos calculados conferem com os digitos informados.

            if ((dig10 == cpfSemPontuacao.charAt(9)) && (dig11 == cpfSemPontuacao.charAt(10)))
                return (true);
            else
                return (false);
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

    public static String imprimeCPF(String cpf) throws CpfInvalidoException {

        String cpfSemPontuacao = cpf.replaceAll("[^0-9]", "");

        if (verificarCpf(cpfSemPontuacao) == true) {
            return (cpfSemPontuacao.substring(0, 3) + "." + cpfSemPontuacao.substring(3, 6) + "." +
                    cpfSemPontuacao.substring(6, 9) + "-" + cpfSemPontuacao.substring(9, 11));
        } else {
            throw new CpfInvalidoException();
        }
    }

    public static String unFormatCpf(String cpf) throws CpfInvalidoException {
        if (verificarCpf(cpf) == true) {
            String cpfSemPontuacao = cpf.replaceAll("[^0-9]", "");
            return cpfSemPontuacao;
        } else {
            throw new CpfInvalidoException();
        }
    }

    public static String verificarTelefone(String telefone) throws TelefoneInvalidoException {
        String telefoneSemFormatacao = telefone.replaceAll("[^0-9]", "");
        if (telefoneSemFormatacao.length() == 11) {
            return "(" + telefoneSemFormatacao.substring(0, 2) + ")" +
                    telefoneSemFormatacao.substring(2, 7) + "-" +
                    telefoneSemFormatacao.substring(7, 11);
        } else {
            throw new TelefoneInvalidoException();
        }
    }

    // Verifica a maioridade

    public boolean verificarMaioridade() {

        LocalDate dataAtual = LocalDate.now();

        Period periodo = Period.between(getDataNascimento(), dataAtual);

        if (periodo.getYears() >= 18) {
            return true;
        } else {
            return false;
        }
    }

    public String verificarMaioridadeToString() {
        if (verificarMaioridade()) {
            return "É maior de idade.";
        } else {
            return "É menor de idade.";
        }
    }

    public LocalDate addLocalDate(int dd, int mm, int yyyy) {
        LocalDate data = LocalDate.of(yyyy, mm, dd);
        return data;
    }

    public String nomeValido(String input) throws NameNotNullOrInvalidException {
        if (input == null || input.trim().isEmpty()) {
            throw new NameNotNullOrInvalidException();
        }
        String lettersAndSpacesPattern = "^[a-zA-Zà-úÀ-ÚâêîôûãõáéíóúçüÁÉÍÓÚÇÜ ]+$";
        Pattern pattern = Pattern.compile(lettersAndSpacesPattern);
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            return input;
        } else {
            throw new NameNotNullOrInvalidException();
        }
    }

    public String toString() {
        return "Nome: " + getNome() + "\tCPF: " + imprimeCPF(getCpf())
                + "\tData de Nascimento: " + dataNascimentoFormatada() + "\tTelefone: "
                + verificarTelefone(getTelefone());
    }

}
