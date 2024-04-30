package proj;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Acompanhante extends Pessoa {
    private String idAcompanhante = gerarIdAcompanhante();
    private Cliente responsavel;

    // Getters and setters

    public Acompanhante(String nome, String cpf, String telefone, LocalDate dataNascimento, Cliente responsavel) {
        super(nome, cpf, telefone, dataNascimento);
        this.responsavel = responsavel;
    }

    // Fim dos Getters and setters

    public Cliente getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Cliente responsavel) {
        this.responsavel = responsavel;
    }

    // Fim dos Getters and setters

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

    public String toString() {
        return "Nome: " + getNome() + "\nId: " + idAcompanhante + "\nCPF: " + imprimeCPF(getCpf())
                + "\nData de Nascimento: "
                + dataNascimentoFormatada() + "\nTelefone: " + imprimirTelefone(getTelefone()) + "\nResponsável: "
                + responsavel
                + "\nÉ de maior? " + verificarMaioridadeFeedback();
    }

}
