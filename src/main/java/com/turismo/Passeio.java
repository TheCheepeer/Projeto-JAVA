package com.turismo;

import java.text.NumberFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.execoes.DataPassadaException;

public class Passeio {
    private int idPasseio, idDestino, idOnibus;
    private float preco;
    private LocalDate data;
    private LocalTime hora;

    public Passeio(int idPasseio, int idDestino, int idOnibus, float preco, LocalDate data, LocalTime hora) {
        this.idPasseio = idPasseio;
        this.idDestino = idDestino;
        this.idOnibus = idOnibus;
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

    public int getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(int idDestino) {
        this.idDestino = idDestino;
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

    public LocalDate addLocalDate(int dd, int mm, int yyyy) throws DataPassadaException {
        LocalDate data = LocalDate.of(yyyy, mm, dd);
        LocalDate atual = LocalDate.now();
        if (data.isBefore(atual)) {
            throw new DataPassadaException();
        }
        return data;
    }

    public LocalTime addLocalTime(int hours, int minutes) {
        if (hours < 0 || hours > 23 || minutes < 0 || minutes > 59) {
            throw new DateTimeException("Hora ou minutos inválidos.");
        }
        LocalTime hora = LocalTime.of(hours, minutes);
        return hora;
    }

    public static String precoFormatado(float preco) {
        Locale locale = new Locale("pt", "BR");
        NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(locale);
        return formatoMoeda.format(preco);
    }

    public String toString() {
        return "IdPasseio: " + idPasseio + " IdDestino: " + idDestino + " IdOnibus: "
                + idOnibus + " Data: " + dataFormatada() + " Hora: " + horaF()
                + " Preço: " + precoFormatado(preco) + " Inscrições: " + situacaoInscricoes();
    }
}
