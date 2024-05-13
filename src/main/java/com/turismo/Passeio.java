package com.turismo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Passeio {
    private String idPasseio;
    private Destino destino;
    private Onibus onibus;
    private float preco;
    private int qtdCliente;
    private Cliente cliente;
    private LocalDate data;
    private LocalTime horario;
    private Pagamento pagamento;

    public Passeio(String idPasseio, Destino destino, Onibus onibus, float preco, int qtdCliente, Cliente cliente,
            LocalDate data, LocalTime horario, Pagamento pagamento) {
        this.idPasseio = idPasseio;
        this.destino = destino;
        this.onibus = onibus;
        this.preco = preco;
        this.qtdCliente = qtdCliente;
        this.cliente = cliente;
        this.data = data;
        this.horario = horario;
        this.pagamento = pagamento;
    }

    // Getters and setters

    public String getIdPasseio() {
        return idPasseio;
    }

    public void setIdPasseio(String idPasseio) {
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

    public int getQtdCliente() {
        return qtdCliente;
    }

    public void setQtdCliente(int qtdCliente) {
        this.qtdCliente = qtdCliente;
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

    public String gerarIdPasseio() {
        Random random = new Random();
        Set<String> cadastros = new HashSet<>();

        do {
            int parte1 = 100 + random.nextInt(900);
            int parte2 = 10 + random.nextInt(10);
            int parte3 = 100 + random.nextInt(900);

            idPasseio = "P" + parte1 + "-" + parte2 + "-" + parte3;
        } while (!cadastros.add(idPasseio));

        return idPasseio;
    }

    // Agendar Passeio

    public void agendarPasseio() {

    }
}
