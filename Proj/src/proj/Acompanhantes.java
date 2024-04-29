package proj;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Acompanhantes {
    private String nome, idAcompanhante = gerarIdAcompanhante(), cpf, telefone;
    private LocalDate dataNascimento;
    private Cliente responsavel;

    public Acompanhantes(String nome, String cpf, String telefone, LocalDate dataNascimento,
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

    // Gera Id dos Acompanhantes

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

    public boolean verificarCpf(String cpf) {
        int[] pesosPrimeiroDígito = { 10, 9, 8, 7, 6, 5, 4, 3, 2 };
        int[] pesosSegundoDígito = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };

        // Primeiro Dígito

        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Integer.parseInt(cpf.substring(i, i + 1)) * pesosPrimeiroDígito[i];
        }

        int resto = 11 - (soma % 11);
        if (resto == 10 || resto == 11) {
            resto = 0;
        }

        if (resto != Integer.parseInt(cpf.substring(9, 10))) {
            return false;
        }

        // Segundo Dígito

        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Integer.parseInt(cpf.substring(i, i + 1)) * pesosSegundoDígito[i];
        }

        resto = 11 - (soma % 11);
        if (resto == 10 || resto == 11) {
            resto = 0;
        }

        return resto == Integer.parseInt(cpf.substring(10));
    }

    public String toString() {
        return "Nome: " + nome + "\nId: " + idAcompanhante + "\nCPF: " + cpf + "\nData de Nascimento: "
                + dataNascimentoFormatada() + "\nTelefone: " + telefone + "\nResponsável: " + responsavel
                + "\nÉ de maior? " + verificarMaioridadeFeedback();
    }

}
