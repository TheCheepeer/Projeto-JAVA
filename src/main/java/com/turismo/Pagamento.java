package com.turismo;

public class Pagamento {
    private int idPagamento;
    private int idPasseio;
    private int idCliente;
    private boolean situacao;

    public Pagamento(int idPagamento, int idPasseio, int idCliente, boolean situacao) {
        this.idPagamento = idPagamento;
        this.idPasseio = idPasseio;
        this.idCliente = idCliente;
        this.situacao = situacao;
    }

    // Getters and setters

    public int getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(int idPagamento) {
        this.idPagamento = idPagamento;
    }

    public int getIdPasseio() {
        return idPasseio;
    }

    public void setIdPasseio(int idPasseio) {
        this.idPasseio = idPasseio;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public boolean getSituacao() {
        return situacao;
    }

    public void setSituacao(boolean situacao) {
        this.situacao = situacao;
    }

    // Fim dos Getters and setters

    public String situacaoToString() {
        if (situacao == true) {
            return "Pagamento efetuado";
        } else {
            return "Pagamento não efetuado";
        }

    }

    public String toString() {
        return "IdPagamento: " + idPagamento + "\tIdPasseio: " + idPasseio + "\tIdCliente: " + idCliente
                + "\tSituação: " + situacaoToString();
    }

}
