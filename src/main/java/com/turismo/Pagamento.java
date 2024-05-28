package com.turismo;

public class Pagamento {
    private int idPagamento;

    private int idPasseio;
    private int idCliente;
    private boolean pagou;

    public Pagamento(int idPagamento, int idPasseio, int idCliente, boolean pagou) {
        this.idPagamento = idPagamento;
        this.idPasseio = idPasseio;
        this.idCliente = idCliente;
        this.pagou = pagou;
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

    public boolean getPagou() {
        return pagou;
    }

    public void setPagou(boolean pagou) {
        this.pagou = pagou;
    }

    // Fim dos Getters and setters

    public String situacao() {
        if (pagou == true) {
            return "Pagamento efetuado";
        } else {
            return "Pagamento não efetuado";
        }

    }

    public String toString() {
        return "IdPagamento: " + idPagamento + "\tIdPasseio: " + idPasseio + "\tIdCliente: " + idCliente
                + "\tSituação: " + situacao();
    }

}
