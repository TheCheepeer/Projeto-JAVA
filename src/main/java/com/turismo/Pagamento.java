package com.turismo;

public class Pagamento {
    private int idPagamento;

    private Passeio passeio;
    private Cliente cliente;
    private boolean pagou;

    public Pagamento(int idPagamento, Passeio passeio, Cliente cliente, boolean pagou) {
        this.idPagamento = idPagamento;
        this.passeio = passeio;
        this.cliente = cliente;
        this.pagou = pagou;
    }

    // Getters and setters

    public int getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(int idPagamento) {
        this.idPagamento = idPagamento;
    }

    public Passeio getPasseio() {
        return passeio;
    }

    public void setPasseio(Passeio passeio) {
        this.passeio = passeio;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public boolean isPagou() {
        return pagou;
    }

    public void setPagou(boolean pagou) {
        this.pagou = pagou;
    }

    // Fim dos Getters and setters

}
