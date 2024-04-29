package proj;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Acompanhantes {
    private String nome, idAcompanhante, telefone;
    private Date dataNascimento;
    private Cliente responsavel;

    public Acompanhantes(String nome, String idAcompanhante, String telefone, Date dataNascimento,
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

    public String dataNacimentoFormatada() {
        SimpleDateFormat fomatoNas = new SimpleDateFormat("dd/MM/yyyy");
        return fomatoNas.format(dataNascimento);
    }

    public boolean verificarMaioridade() {
        // Calculo da maioridade

        Date dataAtual = new Date(0);

        long diferencaEmMilissegundos = dataAtual.getTime() - dataNascimento.getTime();
        long idadeEmAnos = diferencaEmMilissegundos / (1000L * 60L * 60L * 24L * 365L);

        // Verifica se a pessoa tem 18 anos ou mais

        return idadeEmAnos >= 18;
    }

}
