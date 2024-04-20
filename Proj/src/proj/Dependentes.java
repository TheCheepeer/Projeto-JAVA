package proj;

import java.sql.Date;

public class Dependentes {
    String nome, idDependente, telefone;
    Date dataNascimento;
    Cliente responsavel;

    public Dependentes(String nome, String idDependente, String telefone, Date dataNascimento, Cliente responsavel) {
        this.nome = nome;
        this.idDependente = idDependente;
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

    public String getIdDependente() {
        return idDependente;
    }

    public void setIdDependente(String idDependente) {
        this.idDependente = idDependente;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
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
