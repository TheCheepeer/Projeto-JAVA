package com.turismo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Passeio {
    private int idPasseio, idCliente, idDestino, idPagamento, idOnibus;
    private float preco;
    private LocalDate data;
    private LocalTime hora;

    public Passeio(int idPasseio, int idDestino, int idOnibus, float preco, int idCliente,
            LocalDate data, LocalTime hora, int idPagamento) {
        this.idPasseio = idPasseio;
        this.idCliente = idCliente;
        this.idDestino = idDestino;
        this.idPagamento = idPagamento;
        this.preco = preco;
        this.data = data;
        this.hora = hora;
    }

    // Getters and setters

    public int getIdOnibus() {
        return idOnibus;
    }

    public void setIdOnibus(int idOnibus) {
        this.idOnibus = idOnibus;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(int idDestino) {
        this.idDestino = idDestino;
    }

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

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    // Fim dos Getters and setters

    public String dataFormatada() {
        DateTimeFormatter dataF = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(dataF);
    }

    public String horaF() {
        DateTimeFormatter horaF = DateTimeFormatter.ofPattern("HH:mm");
        return hora.format(horaF);
    }

    public String situacaoInscricoes() {
        if (LocalDate.now() == data && LocalTime.now() == hora) {
            return "Finalizadas";
        } else {
            return "Em andamento";
        }
    }

    public String toString() {
        return "IdPasseio: " + idPasseio + "\tIdCliente: " + idCliente + "\tIdDestino: " + idDestino + "\tIdOnibus: "
                + idOnibus + "\tIdPagamento: " + idPagamento + "\tData: " + dataFormatada() + "\tHora: " + horaF()
                + "Inscrições: " + situacaoInscricoes();
    }
}
