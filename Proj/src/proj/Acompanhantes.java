package proj;

import java.time.LocalDate;

public class Acompanhantes {
    private String nome, idAcompanhante, telefone;
    private LocalDate dataNascimento;
    private Cliente responsavel;

    public Acompanhantes(String nome, String idAcompanhante, String telefone, LocalDate dataNascimento,
            Cliente responsavel) {
        this.nome = nome;
        this.idAcompanhante = idAcompanhante;
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

    public String getIdAcompanhante() {
        return idAcompanhante;
    }

    public void setIdAcompanhante(String idAcompanhante) {
        this.idAcompanhante = idAcompanhante;
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

}
