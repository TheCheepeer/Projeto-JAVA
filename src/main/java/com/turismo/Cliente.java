package com.turismo;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.execoes.EmailInvalidoException;

public class Cliente extends Pessoa {
    private int idCliente, idEndereco;
    private String email;

    public Cliente(String nome, String cpf, String telefone, LocalDate dataNascimento, int idCliente, String email,
            int idEndereco) {
        super(nome, cpf, telefone, dataNascimento);
        this.idCliente = idCliente;
        this.email = email;
        this.idEndereco = idEndereco;
    }

    // Getters and Setters

    public int getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Fim dos Getters and Setters

    public static String validarEmail(String email) throws EmailInvalidoException {
        if (email == null || email.isEmpty()) {
            return email; // Email é opcional
        } else {
            // valida o formato do email
            String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
            Pattern pattern = Pattern.compile(emailRegex);
            Matcher matcher = pattern.matcher(email);

            // se o email corresponde ao formato esperado
            if (matcher.matches()) {
                return email;
            } else {
                throw new EmailInvalidoException();
            }
        }
    }

    public String fixEmail(String email) {
        if (email == null || email.isEmpty()) {
            return "Sem email";
        } else {
            return email;
        }
    }

    public String toString() {
        return "Id: " + idCliente + " Nome: " + getNome() + " CPF: " + imprimeCPF(getCpf())
                + " Data de Nascimento: " + dataNascimentoFormatada() + " Maioridade: " + verificarMaioridadeToString()
                + " Telefone: "
                + verificarTelefone(getTelefone()) + " Email: " + fixEmail(email) + " Endereco: " + idEndereco;
    }

}
