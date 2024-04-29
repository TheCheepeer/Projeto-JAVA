package proj;

import java.time.LocalDate;

public class Cliente {
    private String nome, idCliente, cpf, email, telefone;
    private LocalDate dataNascimento;
    private Endereco endereco;
    private Passeio passeio;
    private Acompanhantes Acompanhantes;
    private Pagamento pagamento;

    public Cliente(String nome, String idCliente, String cpf, String email, String telefone, LocalDate dataNascimento,
            Endereco endereco, Passeio passeio, Acompanhantes acompanhantes, Pagamento pagamento) {
        this.nome = nome;
        this.idCliente = idCliente;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.passeio = passeio;
        this.Acompanhantes = acompanhantes;
        this.pagamento = pagamento;
    }

    // Getters and Setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Passeio getPasseio() {
        return passeio;
    }

    public void setPasseio(Passeio passeio) {
        this.passeio = passeio;
    }

    public Acompanhantes getAcompanhantes() {
        return Acompanhantes;
    }

    public void setAcompanhantes(Acompanhantes acompanhantes) {
        this.Acompanhantes = acompanhantes;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    // Fim dos Getters and Setters

}
