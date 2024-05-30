package com.tests;

import java.time.LocalDate;

import com.turismo.Cliente;

public class testdate {
    public static void main(String[] args) {
        Cliente cliente = new Cliente(null, null, null, null, 0, null, 0);
        cliente.setEmail(Cliente.validarEmail("lucas@gmail.com"));
        System.out.println(cliente.getEmail());
    }
}
