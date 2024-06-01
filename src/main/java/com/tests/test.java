package com.tests;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.DateTimeException;
import java.util.Scanner;

import com.turismo.*;
import com.banco.ClienteDao;
import com.banco.Database;
import com.banco.EnderecoDao;
import com.execoes.CepInvalidoException;
import com.execoes.CpfInvalidoException;
import com.execoes.EmailInvalidoException;
import com.execoes.NameNotNullOrInvalidException;
import com.execoes.RegistroJaExistenteException;
import com.execoes.SemConexaoInternetException;
import com.execoes.TelefoneInvalidoException;

public class test {
    public static void main(String[] args) {
        Connection con = null;
        Scanner scanner = new Scanner(System.in);

        try {
            con = Database.getInstance().getConnection();

            Statement statement = con.createStatement();
            statement.setQueryTimeout(30); // set timeout to 30 sec.

            int opcoes = -1;
            do {
                do {
                    System.out.println(
                            "\n1. Opções Cliente \n2. Opções Passeio\n3. Opções Pagamento\n0. Para sair\n");
                    System.out.println("\nDigite uma opção\n");
                    String opcoesStr = scanner.nextLine();
                    try {
                        opcoes = Integer.parseInt(opcoesStr);

                    } catch (NumberFormatException e) {
                        System.err.println("\nInválido, digite um número válido!");
                    }
                } while (opcoes != 0 && opcoes != 1 && opcoes != 2 && opcoes != 3);

                switch (opcoes) {
                    case 1:
                        // Opções Cliente
                        int opcoes2 = -1;

                        do {
                            do {
                                try {
                                    System.out.println(
                                            "\n1. Cadastrar Cliente \n2. Buscar Cliente por id\n3. Buscar Cliente por CPF\n4. Atualizar dados do Cliente\n5. Excluir Cliente\n");
                                    String opcoes2String = scanner.nextLine();
                                    opcoes2 = Integer.parseInt(opcoes2String);

                                } catch (NumberFormatException e) {
                                    System.err.println("\nInválido, digite um número válido!\n");
                                }

                            } while (opcoes2 != 0 && opcoes2 != 1 && opcoes2 != 2 && opcoes2 != 3 && opcoes2 != 4
                                    && opcoes2 != 5);

                            switch (opcoes2) {

                                // Cadastrar Cliente

                                case 1:
                                    boolean finalizar = false;
                                    do {

                                        Cliente cliente = new Cliente(null, null, null, null, 0, null, 0);
                                        boolean finalizar2;
                                        do {
                                            finalizar2 = false;
                                            try {
                                                System.out.println("\nDigite o nome:\n");
                                                cliente.setNome(cliente.nameNotNullOrInvalid(scanner.nextLine()));
                                                finalizar2 = true;
                                            } catch (NameNotNullOrInvalidException e) {
                                                System.err.println("\nNome nulo ou com números");
                                                continue;
                                            }
                                        } while (!finalizar2);
                                        do {
                                            try {
                                                finalizar2 = false;
                                                System.out.println("\nDigite o cpf:\n");
                                                cliente.setCpf(Cliente.unFormatCpf(scanner.nextLine()));
                                                finalizar2 = true;
                                            } catch (CpfInvalidoException e) {
                                                System.err
                                                        .println("\nCpf Inválido. Verifique e tente novamente.\n");
                                                continue;
                                            }

                                        } while (!finalizar2);

                                        do {
                                            finalizar2 = false;
                                            try {
                                                System.out.println("\nDigite o telefone:\n");
                                                cliente.setTelefone(Cliente.imprimirTelefone(scanner.nextLine()));
                                                finalizar2 = true;
                                            } catch (TelefoneInvalidoException e) {
                                                System.err.println(
                                                        "\nTelefone inválido. Verifique e tente novamente\n");
                                                continue;
                                            }
                                        } while (!finalizar2);
                                        do {
                                            finalizar2 = false;
                                            try {
                                                System.out.println(
                                                        "\nDigite a data de nascimento no formato DD/MM/AAAA\n");
                                                System.out.println("\nDigite o dia:\n");
                                                String ddString = scanner.nextLine();
                                                int dd = Integer.parseInt(ddString);
                                                System.out.println("\nDigite o mês:\n");
                                                String mmString = scanner.nextLine();
                                                int mm = Integer.parseInt(mmString);
                                                System.out.println("\nDigite o ano\n");
                                                String yyyyString = scanner.nextLine();
                                                int yyyy = Integer.parseInt(yyyyString);
                                                cliente.setDataNascimento(cliente.addLocalDate(dd, mm, yyyy));
                                                finalizar2 = true;
                                            } catch (DateTimeException e) {
                                                System.out.println("\nDigite uma data válida!\n");
                                                continue;
                                            } catch (NumberFormatException e) {
                                                System.err.println("\nDigite uma data válida!\n");
                                                scanner.nextLine();
                                                continue;
                                            }

                                        } while (!finalizar2);

                                        do {
                                            finalizar2 = false;
                                            try {
                                                System.out.println("\nDigite o Email(opcional):\n");
                                                cliente.setEmail(Cliente.validarEmail(scanner.nextLine()));
                                                finalizar2 = true;
                                            } catch (EmailInvalidoException e) {
                                                System.err.println("\nDigite um email válido\n");
                                                continue;
                                            }
                                        } while (!finalizar2);

                                        // Endereço Cliente

                                        Endereco endereco = new Endereco(0, null, null, 0, null, null, null, null);
                                        int caminho = -1;
                                        do {
                                            try {
                                                System.out.println("\nDigite o CEP:\n");
                                                endereco.setCep(scanner.nextLine());
                                                endereco.consultarCEP();
                                                System.out.println("\nResultados do CEP:\n");
                                                System.out.println("\nlogradouro: " + endereco.getLogradouro());
                                                System.out.println("Bairro: " + endereco.getBairro());
                                                System.out.println("Cidade: " + endereco.getCidade());
                                                System.out.println("Uf: " + endereco.getUf());
                                                System.out.println(
                                                        "\nOs dados conferem?\n\n1. Sim\n2. Não\n3. Colocar CEP novamente.\n");

                                                String caminhoStr = scanner.nextLine();

                                                try {
                                                    caminho = Integer.parseInt(caminhoStr); // Conversão para número
                                                } catch (NumberFormatException e) {
                                                    System.err.println("\nOpção inválida! Tente novamente\n");
                                                    continue;
                                                }

                                                while (caminho != 1 && caminho != 2 && caminho != 3) {
                                                    System.out.println("\nOpção inválida! Tente novamente\n");
                                                    caminhoStr = scanner.nextLine(); // Leitura como string
                                                    try {
                                                        caminho = Integer.parseInt(caminhoStr);
                                                    } catch (NumberFormatException e) {
                                                        System.err.println("\nOpção inválida! Tente novamente\n");
                                                        continue;
                                                    }
                                                }

                                            } catch (SemConexaoInternetException e) {
                                                System.err.println(
                                                        "\nSem conexão com a internet.\n\nPor favor, digite manualmente.\n");
                                                caminho = 2;
                                            } catch (IllegalArgumentException e) {
                                                System.err.println("\nO CEP deve conter 8 digitos.\n");
                                                continue;
                                            } catch (CepInvalidoException e) {
                                                System.err.println("\nCEP inesistente!\n");
                                                continue;
                                            }
                                        } while (caminho != 1 && caminho != 2);

                                        if (caminho == 2) {

                                            do {
                                                finalizar2 = false;
                                                try {
                                                    System.out.println("\nDigite o Logradouro:\n");
                                                    endereco.setLogradouro(endereco.nameNotNull(scanner.nextLine()));
                                                    System.out.println("\nDigite o Número:\n");
                                                    String numString = scanner.nextLine();
                                                    int num = Integer.parseInt(numString);
                                                    endereco.setNumero(num);
                                                    System.out.println("\nDigite o complemento (Opcional):\n");
                                                    endereco.setComplemento(scanner.nextLine());
                                                    System.out.println("\nDigite o Bairro:\n");
                                                    endereco.setBairro(endereco.nameNotNull(scanner.nextLine()));
                                                    System.out.println("\nDigite a cidade:\n");
                                                    endereco.setCidade(endereco.nameNotNull(scanner.nextLine()));
                                                    System.out.println("\nDigite a UF:\n");
                                                    endereco.setUf(endereco.nameNotNull(scanner.nextLine()));

                                                    finalizar2 = true;
                                                } catch (NumberFormatException e) {
                                                    System.err.println(
                                                            "\nApenas números são permitidos para o campo Número!\n");
                                                    scanner.nextLine();
                                                    continue;
                                                } catch (NameNotNullOrInvalidException e) {
                                                    System.err.println("\nCampo nulo ou inválido! Tente novamente\n");
                                                    continue;
                                                }
                                            } while (!finalizar2);
                                        } else {
                                            do {
                                                finalizar2 = false;
                                                try {
                                                    System.out.println("\nDigite o Número:\n");
                                                    String numString = scanner.nextLine();
                                                    int num = Integer.parseInt(numString);
                                                    endereco.setNumero(num);
                                                    System.out.println("\nDigite o complemento (Opcional):\n");
                                                    endereco.setComplemento(
                                                            endereco.fixComplemento(scanner.nextLine()));
                                                    finalizar2 = true;
                                                } catch (NumberFormatException e) {
                                                    System.err.println(
                                                            "\nApenas números são permitidos para o campo Número!\n");
                                                    continue;
                                                }

                                            } while (!finalizar2);
                                        }
                                        // TODO: Fazer consertar o que há de errado aqui!
                                        EnderecoDao enderecoDao = new EnderecoDao();
                                        ClienteDao clienteDao = new ClienteDao();
                                        try {
                                            enderecoDao.verificarExistencia(endereco.getCep(), endereco.getLogradouro(),
                                                    endereco.getNumero(), endereco.getComplemento(),
                                                    endereco.getBairro(), endereco.getCidade(), endereco.getUf());
                                            enderecoDao.inserir(endereco);
                                            endereco = enderecoDao.getLast();
                                            cliente.setIdEndereco(endereco.getIdEndereco());
                                        } catch (RegistroJaExistenteException e) {
                                            System.err.println("Endereco subs");
                                            endereco = enderecoDao.getOnInfo(endereco.getCep(),
                                                    endereco.getLogradouro(),
                                                    endereco.getNumero(), endereco.getComplemento(),
                                                    endereco.getBairro(), endereco.getCidade(), endereco.getUf());
                                            cliente.setIdEndereco(endereco.getIdEndereco());
                                        }
                                        try {
                                            finalizar2 = false;
                                            clienteDao.verificarExistencia(cliente.getCpf());
                                            clienteDao.inserir(cliente);
                                            cliente = clienteDao.getLast();
                                        } catch (RegistroJaExistenteException e) {
                                            System.err.println("Registro já existente, tente novamente.");
                                            finalizar = true;
                                            opcoes2 = 0;
                                        }
                                        if (finalizar2) {
                                            System.out.println("\n" + cliente);
                                            System.out.println(endereco);

                                            do {

                                                try {
                                                    System.out.println("\nAs informações conferem?\n\n1. Sim\n2. Não");
                                                    String caminhoStr = scanner.nextLine();

                                                    caminho = Integer.parseInt(caminhoStr);
                                                    while (caminho != 1 && caminho != 2) {
                                                        System.err.println("\nInválido, tente novamente.\n");
                                                        finalizar2 = false;
                                                    }
                                                    finalizar2 = true;
                                                } catch (NumberFormatException e) {
                                                    System.err.println("\nOpção inválida! Tente novamente\n");
                                                    continue;
                                                }
                                            } while (!finalizar2);
                                            if (caminho == 1) {
                                                finalizar = true;
                                            } else {
                                                continue;
                                            }
                                        }
                                    } while (!finalizar);

                                    // Fim de Cadastrar Cliente

                                    break;

                                case 2:
                                    break;
                            }

                        } while (opcoes2 != 0);

                        break;
                    case 2:
                        // Opções Passeio
                        break;
                    case 3:
                        // Opções Pagamento
                        break;
                }

            } while (opcoes != 0);

            System.out.println("TUDO PRONTO!!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                scanner.close();
                System.exit(0);
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
}
