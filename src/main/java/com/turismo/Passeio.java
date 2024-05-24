package com.turismo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Passeio {
    private int idPasseio;
    private Destino destino;
    private Onibus onibus;
    private float preco;
    private Cliente cliente;
    private LocalDate data;
    private LocalTime horario;
    private Pagamento pagamento;

    public Passeio(int idPasseio, Destino destino, Onibus onibus, float preco, int qtdCliente, Cliente cliente,
            LocalDate data, LocalTime horario, Pagamento pagamento) {
        this.idPasseio = idPasseio;
        this.destino = destino;
        this.onibus = onibus;
        this.preco = preco;
        this.cliente = cliente;
        this.data = data;
        this.horario = horario;
        this.pagamento = pagamento;
    }

    // Getters and setters

    public int getIdPasseio() {
        return idPasseio;
    }

    public void setIdPasseio(int idPasseio) {
        this.idPasseio = idPasseio;
    }

    public Destino getDestino() {
        return destino;
    }

    public void setDestino(Destino destino) {
        this.destino = destino;
    }

    public Onibus getOnibus() {
        return onibus;
    }

    public void setOnibus(Onibus onibus) {
        this.onibus = onibus;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    // Fim dos Getters and setters

    // Agendar Passeio

    public void agendarPasseio() {

    }
}
