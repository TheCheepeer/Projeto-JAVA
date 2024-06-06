package com.turismo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.DateTimeException;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

import com.banco.ClienteDao;
import com.banco.Database;
import com.banco.DestinoDao;
import com.banco.EnderecoDao;
import com.banco.OnibusDao;
import com.banco.PagamentoDao;
import com.banco.PasseioDao;
import com.execoes.CepInvalidoException;
import com.execoes.CpfInvalidoException;
import com.execoes.DataPassadaException;
import com.execoes.ElementoNaoEncontradoExption;
import com.execoes.EmailInvalidoException;
import com.execoes.NameNotNullOrInvalidException;
import com.execoes.RegistroJaExistenteException;
import com.execoes.SemConexaoInternetException;
import com.execoes.TelefoneInvalidoException;
import com.ferramentas.Ferramentas;

public class Main {
    public static void main(String[] args) {
        Connection con = null;
        Scanner scanner = new Scanner(System.in);

        try {
            con = Database.getInstance().getConnection();

            Statement statement = con.createStatement();
            statement.setQueryTimeout(30);

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
                        Ferramentas.clearConsole();
                        System.err.println("\nInválido, digite um número válido!");
                    }
                } while (opcoes != 0 && opcoes != 1 && opcoes != 2 && opcoes != 3);

                switch (opcoes) {
                    case 1:
                        // Opções Cliente
                        int opcoes2 = -1;
                        // TODO: Ver opções
                        do {
                            Ferramentas.clearConsole();
                            do {
                                try {
                                    System.out.println(
                                            "\n1. Cadastrar Cliente \n2. Buscar Cliente\n3. Atualizar dados do Cliente\n4. Excluir Cliente\n5. Listar Clientes\n0. Voltar\n");
                                    String opcoes2String = scanner.nextLine();
                                    opcoes2 = Integer.parseInt(opcoes2String);

                                } catch (NumberFormatException e) {
                                    Ferramentas.clearConsole();
                                    System.err.println("\nInválido, digite um número válido!\n");
                                }

                            } while (opcoes2 != 0 && opcoes2 != 1 && opcoes2 != 2 && opcoes2 != 3 && opcoes2 != 4
                                    && opcoes2 != 5);

                            switch (opcoes2) {

                                // Cadastrar Cliente

                                case 1:
                                    boolean finalizar = false;
                                    Ferramentas.clearConsole();
                                    do {

                                        Cliente cliente = new Cliente(null, null, null, null, 0, null, 0);
                                        boolean finalizar2;
                                        do {
                                            finalizar2 = false;
                                            try {
                                                System.out.println("\nDigite o nome:\n");
                                                cliente.setNome(cliente.nomeValido(scanner.nextLine()));
                                                finalizar2 = true;
                                            } catch (NameNotNullOrInvalidException e) {
                                                Ferramentas.clearConsole();
                                                System.err.println("\nNome nulo ou com números\n");
                                                continue;
                                            }
                                        } while (!finalizar2);
                                        Ferramentas.clearConsole();
                                        do {
                                            try {
                                                finalizar2 = false;
                                                System.out.println("\nDigite o cpf:\n");
                                                cliente.setCpf(Cliente.unFormatCpf(scanner.nextLine()));
                                                finalizar2 = true;
                                            } catch (CpfInvalidoException e) {
                                                Ferramentas.clearConsole();
                                                System.err
                                                        .println("\nCpf Inválido. Verifique e tente novamente.\n");
                                                continue;
                                            }

                                        } while (!finalizar2);
                                        Ferramentas.clearConsole();
                                        do {
                                            finalizar2 = false;
                                            try {
                                                System.out.println("\nDigite o telefone:\n");
                                                cliente.setTelefone(Cliente.imprimirTelefone(scanner.nextLine()));
                                                finalizar2 = true;
                                            } catch (TelefoneInvalidoException e) {
                                                Ferramentas.clearConsole();
                                                System.err.println(
                                                        "\nTelefone inválido. Verifique e tente novamente\n");
                                                continue;
                                            }
                                        } while (!finalizar2);
                                        Ferramentas.clearConsole();
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
                                                Ferramentas.clearConsole();
                                                System.out.println("\nDigite uma data válida!\n");
                                                continue;
                                            } catch (NumberFormatException e) {
                                                Ferramentas.clearConsole();
                                                System.err.println("\nDigite uma data válida!\n");
                                                continue;
                                            }

                                        } while (!finalizar2);

                                        Ferramentas.clearConsole();

                                        do {
                                            finalizar2 = false;
                                            try {
                                                System.out.println("\nDigite o Email(opcional):\n");
                                                cliente.setEmail(Cliente.validarEmail(scanner.nextLine()));
                                                finalizar2 = true;
                                            } catch (EmailInvalidoException e) {
                                                Ferramentas.clearConsole();
                                                System.err.println("\nDigite um email válido\n");
                                                continue;
                                            }
                                        } while (!finalizar2);

                                        // Endereço Cliente

                                        Endereco endereco = new Endereco(0, null, null, 0, null, null, null);
                                        int caminho = -1;
                                        Ferramentas.clearConsole();
                                        do {
                                            try {
                                                System.out.println("\nDigite o CEP:\n");
                                                endereco.setCep(scanner.nextLine());
                                                endereco.consultarCEP();
                                                Ferramentas.clearConsole();
                                                System.out.println(endereco.imprimirConsulta());

                                                String caminhoStr = scanner.nextLine();

                                                try {
                                                    caminho = Integer.parseInt(caminhoStr);
                                                } catch (NumberFormatException e) {
                                                    Ferramentas.clearConsole();
                                                    System.err.println("\nOpção inválida! Tente novamente\n");
                                                    continue;
                                                }

                                                while (caminho != 1 && caminho != 2 && caminho != 3) {
                                                    System.out.println("\nOpção inválida! Tente novamente\n");
                                                    caminhoStr = scanner.nextLine();
                                                    try {
                                                        caminho = Integer.parseInt(caminhoStr);
                                                    } catch (NumberFormatException e) {
                                                        System.err.println("\nOpção inválida! Tente novamente\n");
                                                        continue;
                                                    }
                                                }

                                            } catch (SemConexaoInternetException e) {
                                                Ferramentas.clearConsole();
                                                System.err.println(
                                                        "\nSem conexão com a internet.\n\nPor favor, digite manualmente.\n");
                                                caminho = 2;
                                            } catch (IllegalArgumentException e) {
                                                Ferramentas.clearConsole();
                                                System.err.println("\nO CEP deve conter 8 digitos.\n");
                                                continue;
                                            } catch (CepInvalidoException e) {
                                                Ferramentas.clearConsole();
                                                System.err.println("\nCEP inesistente!\n");
                                                continue;
                                            }
                                        } while (caminho != 1 && caminho != 2);

                                        if (caminho == 2) {
                                            Ferramentas.clearConsole();
                                            do {
                                                finalizar2 = false;
                                                try {
                                                    System.out.println("\nDigite o Logradouro:\n");
                                                    endereco.setLogradouro(endereco.nameNotNull(scanner.nextLine()));
                                                    Ferramentas.clearConsole();
                                                    System.out.println("\nDigite o Número:\n");
                                                    String numString = scanner.nextLine();
                                                    int num = Integer.parseInt(numString);
                                                    endereco.setNumero(num);
                                                    Ferramentas.clearConsole();
                                                    System.out.println("\nDigite o Bairro:\n");
                                                    endereco.setBairro(endereco.nameNotNull(scanner.nextLine()));
                                                    Ferramentas.clearConsole();
                                                    System.out.println("\nDigite a cidade:\n");
                                                    endereco.setCidade(endereco.nameNotNull(scanner.nextLine()));
                                                    Ferramentas.clearConsole();
                                                    System.out.println("\nDigite a UF:\n");
                                                    endereco.setUf(endereco.nameNotNull(scanner.nextLine()));
                                                    Ferramentas.clearConsole();

                                                    finalizar2 = true;
                                                } catch (NumberFormatException e) {
                                                    Ferramentas.clearConsole();
                                                    System.err.println(
                                                            "\nApenas números são permitidos para o campo Número!\n");
                                                    continue;
                                                } catch (NameNotNullOrInvalidException e) {
                                                    Ferramentas.clearConsole();
                                                    System.err.println("\nCampo nulo ou inválido! Tente novamente\n");
                                                    continue;
                                                }
                                            } while (!finalizar2);
                                        } else {
                                            Ferramentas.clearConsole();
                                            do {
                                                finalizar2 = false;
                                                try {
                                                    System.out.println("\nDigite o Número:\n");
                                                    String numString = scanner.nextLine();
                                                    int num = Integer.parseInt(numString);
                                                    endereco.setNumero(num);
                                                    finalizar2 = true;
                                                } catch (NumberFormatException e) {
                                                    Ferramentas.clearConsole();
                                                    System.err.println(
                                                            "\nApenas números são permitidos para o campo Número!\n");
                                                    continue;
                                                }

                                            } while (!finalizar2);
                                        }

                                        EnderecoDao enderecoDao = new EnderecoDao();
                                        ClienteDao clienteDao = new ClienteDao();

                                        // Verifica se o endereço já existe e obtém o ID
                                        int idEndereco = enderecoDao.verificarExistenciaEObterId(
                                                endereco.cepF(endereco.getCep()),
                                                endereco.getLogradouro(),
                                                endereco.getNumero(), endereco.getBairro(), endereco.getCidade(),
                                                endereco.getUf());

                                        if (idEndereco != -1) {
                                            // Endereço já existe, usa o ID existente
                                            endereco = enderecoDao.getById(idEndereco);
                                            cliente.setIdEndereco(endereco.getIdEndereco());
                                        } else {
                                            // Endereço não existe, insere no banco de dados e usa o novo ID
                                            enderecoDao.inserir(endereco);
                                            endereco = enderecoDao.getLast();
                                            cliente.setIdEndereco(endereco.getIdEndereco());
                                        }

                                        try {
                                            clienteDao.verificarExistencia(cliente.getCpf());
                                            clienteDao.inserir(cliente);
                                            cliente = clienteDao.getLast();
                                            finalizar = true; // Cliente cadastrado com sucesso, finalizar o loop
                                                              // principal
                                        } catch (RegistroJaExistenteException e) {
                                            Ferramentas.clearConsole();
                                            System.err.println("Registro já existente, tente novamente.");
                                            opcoes2 = 0;
                                        }

                                        if (finalizar) {
                                            System.out.println("\n" + cliente);
                                            System.out.println(endereco);
                                            do {
                                                try {
                                                    System.out
                                                            .println("\nAs informações conferem?\n\n1. Sim\n2. Não\n");
                                                    String caminhoStr = scanner.nextLine();
                                                    caminho = Integer.parseInt(caminhoStr);

                                                    while (caminho != 1 && caminho != 2) {
                                                        System.err.println("\nInválido, tente novamente.\n");
                                                        caminhoStr = scanner.nextLine();
                                                        caminho = Integer.parseInt(caminhoStr);
                                                    }
                                                    finalizar2 = true;
                                                } catch (NumberFormatException e) {
                                                    Ferramentas.clearConsole();
                                                    System.err.println("\nOpção inválida! Tente novamente\n");
                                                }
                                            } while (!finalizar2);

                                            if (caminho == 1) {
                                                finalizar = true;
                                            } else {
                                                clienteDao.delete(cliente.getIdCliente());
                                                finalizar = false;
                                            }
                                        }
                                    } while (!finalizar);

                                    // Fim de Cadastrar Cliente

                                    break;

                                case 2:
                                    finalizar = false;
                                    Ferramentas.clearConsole();
                                    while (!finalizar) {
                                        int caminho = -1;
                                        do {
                                            System.out.println(
                                                    "\n1. Buscar Cliente por Id\n2. Buscar Cliente por Cpf\n0. Voltar\n");
                                            String caminhosStr = scanner.nextLine();
                                            try {
                                                caminho = Integer.parseInt(caminhosStr);
                                            } catch (NumberFormatException e) {
                                                Ferramentas.clearConsole();
                                                System.err.println("\nInválido, digite um número válido!\n");
                                            }
                                        } while (caminho != 1 && caminho != 2 && caminho != 0);

                                        switch (caminho) {
                                            case 1:
                                                Ferramentas.clearConsole();
                                                boolean finalizar2 = false;
                                                do {
                                                    try {
                                                        System.out.println("\nDigite o id do Cliente\n");
                                                        Cliente cliente = new Cliente(null, null, null, null, 0, null,
                                                                0);
                                                        ClienteDao clienteDao = new ClienteDao();
                                                        String idStr = scanner.nextLine();
                                                        int id = Integer.parseInt(idStr);
                                                        cliente = clienteDao.getById(id);

                                                        if (clienteDao.getById(id) != null) {
                                                            System.out.println("\n" + cliente);

                                                            Endereco endereco = new Endereco(id, idStr, idStr, id,
                                                                    idStr,
                                                                    idStr, idStr);
                                                            EnderecoDao enderecoDao = new EnderecoDao();
                                                            endereco = enderecoDao.getById(cliente.getIdEndereco());
                                                            System.out.println(endereco);

                                                            System.out.println("\nAperte enter para sair\n");
                                                            scanner.nextLine();
                                                            Ferramentas.clearConsole();

                                                        } else {
                                                            Ferramentas.clearConsole();
                                                            System.err.println("\nCliente não encontrado.\n");
                                                        }
                                                        finalizar2 = true;

                                                    } catch (NumberFormatException e) {
                                                        Ferramentas.clearConsole();
                                                        System.err.println("\nInválido, digite um número válido!\n");
                                                    }
                                                } while (!finalizar2);
                                                break;

                                            case 2:
                                                Ferramentas.clearConsole();
                                                finalizar2 = false;
                                                do {
                                                    try {
                                                        System.out.println("\nDigite o Cpf do Cliente\n");
                                                        Cliente cliente = new Cliente(null, null, null, null, 0, null,
                                                                0);
                                                        ClienteDao clienteDao = new ClienteDao();
                                                        String cpf = scanner.nextLine();
                                                        cliente = clienteDao
                                                                .getByCpf(Cliente.unFormatCpf(cpf));
                                                        if (clienteDao.getByCpf(Cliente.unFormatCpf(cpf)) != null) {
                                                            System.out.println("\n" + cliente);

                                                            Endereco endereco = new Endereco(opcoes2, cpf, cpf, caminho,
                                                                    cpf, cpf, cpf);
                                                            EnderecoDao enderecoDao = new EnderecoDao();
                                                            endereco = enderecoDao.getById(cliente.getIdEndereco());
                                                            System.out.println(endereco);

                                                            System.out.println("\nAperte enter para sair\n");
                                                            scanner.nextLine();
                                                            Ferramentas.clearConsole();
                                                        } else {
                                                            Ferramentas.clearConsole();
                                                            System.err.println("\nCliente não encontrado.\n");
                                                        }

                                                        finalizar2 = true;

                                                    } catch (CpfInvalidoException e) {
                                                        Ferramentas.clearConsole();
                                                        System.err.println("\nInválido, digite um cpf válido!\n");
                                                    }

                                                } while (!finalizar2);
                                                break;

                                            case 0:
                                                Ferramentas.clearConsole();
                                                finalizar = true;
                                                break;
                                        }
                                    }

                                    break;

                                case 3:
                                    Ferramentas.clearConsole();
                                    int opcoesAt = -1;
                                    do {
                                        try {
                                            System.out.println(
                                                    "\n1. Atualizar email\n2. Atualizar Telefone\n3. Atualizar Endereço\n0. Voltar\n");
                                            String opcoesAtStr = scanner.nextLine();
                                            opcoesAt = Integer.parseInt(opcoesAtStr);
                                        } catch (NumberFormatException e) {
                                            Ferramentas.clearConsole();
                                            System.err.println("\nInválido, digite um número válido!\n");
                                        }
                                    } while (opcoesAt != 1 && opcoesAt != 2 && opcoesAt != 3 && opcoesAt != 0);

                                    switch (opcoesAt) {
                                        case 1:
                                            Ferramentas.clearConsole();
                                            boolean finalizar2 = false;
                                            do {
                                                try {
                                                    System.out.println(
                                                            "\nDigite a id do Cliente\n");
                                                    String idStr = scanner.nextLine();
                                                    int id = Integer.parseInt(idStr);
                                                    Ferramentas.clearConsole();
                                                    Cliente cliente = new Cliente(idStr, idStr, idStr, null, id, idStr,
                                                            id);
                                                    ClienteDao clienteDao = new ClienteDao();
                                                    cliente = clienteDao.getById(id);
                                                    if (clienteDao.getById(id) != null) {
                                                        System.out.println("\nDigite o email do Cliente\n");
                                                        cliente.setEmail(Cliente.validarEmail(scanner.nextLine()));
                                                        clienteDao.updateEmail(cliente, id);
                                                        cliente = clienteDao.getById(id);
                                                        Ferramentas.clearConsole();
                                                        System.out.println("\n" + cliente);
                                                        System.out.println("\nAperte enter para continuar\n");
                                                        scanner.nextLine();
                                                        Ferramentas.clearConsole();
                                                        finalizar2 = true;
                                                    }
                                                } catch (NumberFormatException e) {
                                                    Ferramentas.clearConsole();
                                                    System.err.println("\nInválido, digite um número válido!\n");
                                                } catch (EmailInvalidoException e) {
                                                    Ferramentas.clearConsole();
                                                    System.err.println("\nEmail inválido!\n\nTente novamente\n");
                                                }
                                            } while (!finalizar2);

                                            break;

                                        case 2:
                                            finalizar2 = false;
                                            Ferramentas.clearConsole();
                                            do {
                                                try {
                                                    System.out.println(
                                                            "\nDigite a id do Cliente\n");
                                                    String idStr = scanner.nextLine();
                                                    int id = Integer.parseInt(idStr);
                                                    Ferramentas.clearConsole();
                                                    Cliente cliente = new Cliente(idStr, idStr, idStr, null, id, idStr,
                                                            id);
                                                    ClienteDao clienteDao = new ClienteDao();
                                                    cliente = clienteDao.getById(id);
                                                    if (clienteDao.getById(id) != null) {
                                                        System.out.println("\nDigite o telefone do Cliente\n");
                                                        cliente.setTelefone(
                                                                Cliente.imprimirTelefone(scanner.nextLine()));
                                                        clienteDao.updateTelefone(cliente, id);
                                                        cliente = clienteDao.getById(id);
                                                        Ferramentas.clearConsole();
                                                        System.out.println("\n" + cliente);
                                                        System.out.println("\nAperte enter para continuar\n");
                                                        scanner.nextLine();
                                                        Ferramentas.clearConsole();
                                                        finalizar2 = true;
                                                    } else {
                                                        Ferramentas.clearConsole();
                                                        System.err.println("\nCliente não encontrado.\n");
                                                        finalizar2 = true;
                                                    }
                                                } catch (NumberFormatException e) {
                                                    Ferramentas.clearConsole();
                                                    System.err.println("\nInválido, digite um número válido!\n");
                                                } catch (TelefoneInvalidoException e) {
                                                    Ferramentas.clearConsole();
                                                    System.err.println("\nTelefone inválido!\n\nTente novamente\n");
                                                }
                                            } while (!finalizar2);

                                            break;

                                        case 3:
                                            finalizar2 = false;
                                            Ferramentas.clearConsole();
                                            do {
                                                try {
                                                    System.out.println(
                                                            "\nDigite a id do Cliente\n");
                                                    String idStr = scanner.nextLine();
                                                    int id = Integer.parseInt(idStr);
                                                    Ferramentas.clearConsole();
                                                    Cliente cliente = new Cliente(idStr, idStr, idStr, null, id, idStr,
                                                            id);
                                                    ClienteDao clienteDao = new ClienteDao();
                                                    cliente = clienteDao.getById(id);
                                                    if (clienteDao.getById(id) != null) {
                                                        Ferramentas.clearConsole();
                                                        System.out.println("\nDigite o endereco do Cliente\n");

                                                        Endereco endereco = new Endereco(0, null, null, 0, null, null,
                                                                null);
                                                        int caminho = -1;
                                                        do {
                                                            try {
                                                                System.out.println("\nDigite o CEP:\n");
                                                                endereco.setCep(scanner.nextLine());
                                                                endereco.consultarCEP();
                                                                Ferramentas.clearConsole();
                                                                System.out.println(endereco.imprimirConsulta());

                                                                String caminhoStr = scanner.nextLine();

                                                                try {
                                                                    caminho = Integer.parseInt(caminhoStr);
                                                                } catch (NumberFormatException e) {
                                                                    Ferramentas.clearConsole();
                                                                    System.err.println(
                                                                            "\nOpção inválida! Tente novamente\n");
                                                                    continue;
                                                                }

                                                                while (caminho != 1 && caminho != 2 && caminho != 3) {
                                                                    System.out.println(
                                                                            "\nOpção inválida! Tente novamente\n");
                                                                    caminhoStr = scanner.nextLine();
                                                                    try {
                                                                        caminho = Integer.parseInt(caminhoStr);
                                                                    } catch (NumberFormatException e) {
                                                                        Ferramentas.clearConsole();
                                                                        System.err.println(
                                                                                "\nOpção inválida! Tente novamente\n");
                                                                        continue;
                                                                    }
                                                                }

                                                            } catch (SemConexaoInternetException e) {
                                                                Ferramentas.clearConsole();
                                                                System.err.println(
                                                                        "\nSem conexão com a internet.\n\nPor favor, digite manualmente.\n");
                                                                caminho = 2;
                                                            } catch (IllegalArgumentException e) {
                                                                Ferramentas.clearConsole();
                                                                System.err.println("\nO CEP deve conter 8 digitos.\n");
                                                                continue;
                                                            } catch (CepInvalidoException e) {
                                                                Ferramentas.clearConsole();
                                                                System.err.println("\nCEP inesistente!\n");
                                                                continue;
                                                            }
                                                        } while (caminho != 1 && caminho != 2);

                                                        if (caminho == 2) {
                                                            Ferramentas.clearConsole();
                                                            do {
                                                                finalizar2 = false;
                                                                try {
                                                                    System.out.println("\nDigite o Logradouro:\n");
                                                                    endereco.setLogradouro(
                                                                            endereco.nameNotNull(scanner.nextLine()));
                                                                    Ferramentas.clearConsole();
                                                                    System.out.println("\nDigite o Número:\n");
                                                                    String numString = scanner.nextLine();
                                                                    int num = Integer.parseInt(numString);
                                                                    endereco.setNumero(num);
                                                                    Ferramentas.clearConsole();
                                                                    System.out.println("\nDigite o Bairro:\n");
                                                                    endereco.setBairro(
                                                                            endereco.nameNotNull(scanner.nextLine()));
                                                                    Ferramentas.clearConsole();
                                                                    System.out.println("\nDigite a cidade:\n");
                                                                    endereco.setCidade(
                                                                            endereco.nameNotNull(scanner.nextLine()));
                                                                    Ferramentas.clearConsole();
                                                                    System.out.println("\nDigite a UF:\n");
                                                                    endereco.setUf(
                                                                            endereco.nameNotNull(scanner.nextLine()));
                                                                    Ferramentas.clearConsole();
                                                                    finalizar2 = true;

                                                                } catch (NumberFormatException e) {
                                                                    Ferramentas.clearConsole();
                                                                    System.err.println(
                                                                            "\nApenas números são permitidos para o campo Número!\n");
                                                                    scanner.nextLine();
                                                                    continue;
                                                                } catch (NameNotNullOrInvalidException e) {
                                                                    Ferramentas.clearConsole();
                                                                    System.err.println(
                                                                            "\nCampo nulo ou inválido! Tente novamente\n");
                                                                    continue;
                                                                }
                                                            } while (!finalizar2);
                                                        } else {
                                                            Ferramentas.clearConsole();
                                                            do {
                                                                finalizar2 = false;
                                                                try {
                                                                    System.out.println("\nDigite o Número:\n");
                                                                    String numString = scanner.nextLine();
                                                                    int num = Integer.parseInt(numString);
                                                                    endereco.setNumero(num);
                                                                    Ferramentas.clearConsole();
                                                                    finalizar2 = true;
                                                                } catch (NumberFormatException e) {
                                                                    Ferramentas.clearConsole();
                                                                    System.err.println(
                                                                            "\nApenas números são permitidos para o campo Número!\n");
                                                                    continue;
                                                                }

                                                            } while (!finalizar2);
                                                        }

                                                        EnderecoDao enderecoDao = new EnderecoDao();

                                                        // Verifica se o endereço já existe e obtém o ID
                                                        int idEndereco = enderecoDao.verificarExistenciaEObterId(
                                                                endereco.cepF(endereco.getCep()),
                                                                endereco.getLogradouro(),
                                                                endereco.getNumero(), endereco.getBairro(),
                                                                endereco.getCidade(),
                                                                endereco.getUf());

                                                        if (idEndereco != -1) {
                                                            // Endereço já existe, usa o ID existente
                                                            endereco = enderecoDao.getById(idEndereco);
                                                            cliente.setIdEndereco(endereco.getIdEndereco());
                                                        } else {
                                                            // Endereço não existe, insere no banco de dados e usa o
                                                            // novo ID
                                                            enderecoDao.inserir(endereco);
                                                            endereco = enderecoDao.getLast();
                                                            cliente.setIdEndereco(endereco.getIdEndereco());
                                                        }
                                                        clienteDao.updateEndereco(cliente, id);
                                                        cliente = clienteDao.getById(id);
                                                        Ferramentas.clearConsole();
                                                        System.out.println("\n" + cliente);
                                                        endereco = enderecoDao.getById(cliente.getIdEndereco());
                                                        System.out.println(endereco);
                                                        System.out.println("\nAperte enter para continuar.\n");
                                                        scanner.nextLine();

                                                    } else {
                                                        Ferramentas.clearConsole();
                                                        System.err.println("\nCliente não encontrado.\n");
                                                        finalizar2 = true;
                                                    }
                                                } catch (NumberFormatException e) {
                                                    Ferramentas.clearConsole();
                                                    System.err.println("\nInválido, digite um número válido!\n");
                                                }
                                            } while (!finalizar2);
                                            break;

                                        case 0:
                                            Ferramentas.clearConsole();
                                            finalizar = true;
                                            break;
                                    }

                                    break;

                                case 4:
                                    finalizar = false;
                                    Ferramentas.clearConsole();
                                    do {
                                        try {
                                            System.out.println("\nDigite o id do Cliente\n");
                                            ClienteDao clienteDao = new ClienteDao();
                                            String idStr = scanner.nextLine();
                                            int id = Integer.parseInt(idStr);
                                            clienteDao.delete(id);
                                            System.out.println("\nConcluido!\n\nAperte enter para sair\n");
                                            scanner.nextLine();
                                            finalizar = true;

                                        } catch (NumberFormatException e) {
                                            Ferramentas.clearConsole();
                                            System.err.println("\nInválido, digite um cpf válido!\n");
                                            finalizar = true;
                                        } catch (ElementoNaoEncontradoExption e) {
                                            Ferramentas.clearConsole();
                                            System.err.println("\nCliente não encontrado.\n");
                                        }

                                    } while (!finalizar);

                                    break;

                                case 5:
                                    Ferramentas.clearConsole();
                                    try {
                                        ClienteDao clienteDao = new ClienteDao();
                                        EnderecoDao enderecoDao = new EnderecoDao();
                                        List<Cliente> clientes = clienteDao.getAll();

                                        for (Cliente c : clientes) {
                                            Endereco endereco = enderecoDao.getById(c.getIdEndereco());
                                            System.out.println("\n" + c);
                                            System.out.println(endereco + "\n");
                                            System.out.println(
                                                    "\n-----------------------------------------------------\n");
                                        }

                                        System.out.println("\nAperte enter para sair\n");
                                        scanner.nextLine();

                                    } catch (ElementoNaoEncontradoExption e) {
                                        System.out.println("\nNenhum cliente no banco de dados\n");

                                    }

                                    break;

                            }

                        } while (opcoes2 != 0);
                        // Final de opções cliente

                        break;
                    case 2:
                        // Opções Passeio

                        Ferramentas.clearConsole();
                        opcoes2 = -1;
                        do {

                            System.out.println(
                                    "\nSelecione uma opção\n\n1. Agendar Passeio\n2. Buscar Passeio\n3. Atualizar dados do Passeio\n4. Cancelar Passeio\n5. Listar Passeios\n6. Adicionar Cliente ao Passeio\n0. Voltar\n");

                            try {
                                String opcoes2Str = scanner.nextLine();
                                opcoes2 = Integer.parseInt(opcoes2Str);

                            } catch (NumberFormatException e) {
                                Ferramentas.clearConsole();
                                System.err.println("\nInválido, digite um número válido!\n");
                            }

                        } while (opcoes2 != 1 && opcoes2 != 2 && opcoes2 != 3 && opcoes2 != 4 && opcoes2 != 5
                                && opcoes2 != 6 && opcoes2 != 0);

                        switch (opcoes2) {
                            case 1:
                                // Agendar Passeio

                                boolean finalizar = false;
                                Ferramentas.clearConsole();
                                do {
                                    Passeio passeio = new Passeio(0, 0, 0, 0, null, null);
                                    boolean finalizar2 = false;
                                    do {
                                        try {
                                            System.out.println(
                                                    "\nDigite a data do Passeio no formato DD/MM/AAAA\n");
                                            System.out.println("\nDigite o dia:\n");
                                            String ddString = scanner.nextLine();
                                            int dd = Integer.parseInt(ddString);
                                            System.out.println("\nDigite o mês:\n");
                                            String mmString = scanner.nextLine();
                                            int mm = Integer.parseInt(mmString);
                                            System.out.println("\nDigite o ano\n");
                                            String yyyyString = scanner.nextLine();
                                            int yyyy = Integer.parseInt(yyyyString);
                                            passeio.setData(passeio.addLocalDate(dd, mm, yyyy));
                                            finalizar2 = true;
                                        } catch (DateTimeException e) {
                                            Ferramentas.clearConsole();
                                            System.out.println("\nDigite uma data válida!\n");
                                            continue;
                                        } catch (NumberFormatException e) {
                                            Ferramentas.clearConsole();
                                            System.err.println("\nDigite uma data válida!\n");
                                            continue;
                                        } catch (DataPassadaException e) {
                                            Ferramentas.clearConsole();
                                            System.err.println("A data fornecida está no passado.");
                                        }

                                    } while (!finalizar2);

                                    Ferramentas.clearConsole();

                                    do {
                                        finalizar2 = false;

                                        try {
                                            System.out.println("\nDigite o horário do Passeio no formato hh:mm\n");

                                            System.out.println("\nDigite a Hora:\n");
                                            String horaStr = scanner.nextLine();
                                            int hora = Integer.parseInt(horaStr);

                                            System.out.println("\nDigite os minutos:\n");
                                            String minutosStr = scanner.nextLine();
                                            int minutos = Integer.parseInt(minutosStr);

                                            LocalTime horaPasseio = passeio.addLocalTime(hora, minutos);
                                            passeio.setHora(horaPasseio);
                                            finalizar2 = true;
                                            System.out.println("Horário do passeio definido: " + passeio.getHora());

                                        } catch (NumberFormatException e) {
                                            Ferramentas.clearConsole();
                                            System.err.println("\nDigite um horário válido!\n");
                                        } catch (DateTimeException e) {
                                            Ferramentas.clearConsole();
                                            System.err.println("\nDigite um horário válido!\n");
                                        }

                                    } while (!finalizar2);

                                    Ferramentas.clearConsole();

                                    do {
                                        finalizar2 = false;
                                        System.out.println("\nDigite o preço do passeio(ex: 123,45)\n");
                                        String precoStr = scanner.nextLine();
                                        precoStr = precoStr.replace(",", ".");
                                        try {

                                            float preco = Float.parseFloat(precoStr);
                                            passeio.setPreco(preco);

                                            finalizar2 = true;
                                        } catch (NumberFormatException e) {
                                            Ferramentas.clearConsole();
                                            System.err.println(
                                                    "\nInválido, digite um preço válido\n");
                                        }

                                    } while (!finalizar2);
                                    Ferramentas.clearConsole();
                                    Destino destino = new Destino(0, null, 0);
                                    DestinoDao destinoDao = new DestinoDao();
                                    Endereco endereco = new Endereco(0, null, null, 0, null, null, null);
                                    EnderecoDao enderecoDao = new EnderecoDao();
                                    int escolha = 2;

                                    if (!destinoDao.getAll().isEmpty()) {
                                        do {
                                            System.out.println(
                                                    "\nDeseja adicionar um destino esxistente ou novo?\n\n1.Adicionar destino existente\n2. Adicionar um novo destino\n");
                                            String escolhaStr = scanner.nextLine();
                                            try {
                                                escolha = Integer.parseInt(escolhaStr);

                                            } catch (NumberFormatException e) {
                                                Ferramentas.clearConsole();
                                                System.err.println(
                                                        "\nInválido, digite um número válido\n");
                                            }
                                        } while (escolha != 1 && escolha != 2);
                                    }
                                    Ferramentas.clearConsole();

                                    if (escolha == 1) {
                                        do {
                                            finalizar2 = false;

                                            try {
                                                List<Destino> destinos = destinoDao.getAll();
                                                for (Destino d : destinos) {
                                                    System.out.println(d);
                                                    System.out.println(enderecoDao.getById(d.getIdEndereco()));
                                                    System.out.println(
                                                            "\n-----------------------------------------------------\n");
                                                }

                                                System.out.println(
                                                        "\nDigite a id do Destino\n");
                                                String idStr = scanner.nextLine();
                                                int id = Integer.parseInt(idStr);
                                                Ferramentas.clearConsole();
                                                if (destinoDao.getById(id) != null) {
                                                    destino = destinoDao.getById(id);
                                                    passeio.setIdDestino(destino.getIdDestino());
                                                    finalizar2 = true;
                                                }
                                            } catch (NumberFormatException e) {
                                                Ferramentas.clearConsole();
                                                System.err.println("\nInválido, digite um id válido\n");
                                            }
                                        } while (!finalizar2);
                                    } else {
                                        do {
                                            finalizar2 = false;
                                            System.out.println("\nDigite o nome do Destino\n");
                                            try {
                                                destino.setNome(destino.nomeValido(scanner.nextLine()));
                                                finalizar2 = true;
                                            } catch (NameNotNullOrInvalidException e) {
                                                System.err.println("\nDigite um nome válido\n");
                                            }
                                        } while (!finalizar2);

                                        System.out.println("\nDigite o endereço de destino\n");
                                        int caminho = -1;
                                        do {
                                            try {
                                                System.out.println("\nDigite o CEP:\n");
                                                endereco.setCep(scanner.nextLine());
                                                endereco.consultarCEP();
                                                Ferramentas.clearConsole();
                                                System.out.println(endereco.imprimirConsulta());

                                                String caminhoStr = scanner.nextLine();

                                                try {
                                                    caminho = Integer.parseInt(caminhoStr);
                                                } catch (NumberFormatException e) {
                                                    Ferramentas.clearConsole();
                                                    System.err.println("\nOpção inválida! Tente novamente\n");
                                                    continue;
                                                }

                                                while (caminho != 1 && caminho != 2 && caminho != 3) {
                                                    System.out.println("\nOpção inválida! Tente novamente\n");
                                                    caminhoStr = scanner.nextLine();
                                                    try {
                                                        caminho = Integer.parseInt(caminhoStr);
                                                    } catch (NumberFormatException e) {
                                                        System.err.println("\nOpção inválida! Tente novamente\n");
                                                        continue;
                                                    }
                                                }

                                            } catch (SemConexaoInternetException e) {
                                                Ferramentas.clearConsole();
                                                System.err.println(
                                                        "\nSem conexão com a internet.\n\nPor favor, digite manualmente.\n");
                                                caminho = 2;
                                            } catch (IllegalArgumentException e) {
                                                Ferramentas.clearConsole();
                                                System.err.println("\nO CEP deve conter 8 digitos.\n");
                                                continue;
                                            } catch (CepInvalidoException e) {
                                                Ferramentas.clearConsole();
                                                System.err.println("\nCEP inesistente!\n");
                                                continue;
                                            }
                                        } while (caminho != 1 && caminho != 2);

                                        if (caminho == 2) {
                                            Ferramentas.clearConsole();
                                            do {
                                                finalizar2 = false;
                                                try {
                                                    System.out.println("\nDigite o Logradouro:\n");
                                                    endereco.setLogradouro(endereco.nameNotNull(scanner.nextLine()));
                                                    Ferramentas.clearConsole();
                                                    System.out.println("\nDigite o Número:\n");
                                                    String numString = scanner.nextLine();
                                                    int num = Integer.parseInt(numString);
                                                    endereco.setNumero(num);
                                                    Ferramentas.clearConsole();
                                                    System.out.println("\nDigite o Bairro:\n");
                                                    endereco.setBairro(endereco.nameNotNull(scanner.nextLine()));
                                                    Ferramentas.clearConsole();
                                                    System.out.println("\nDigite a cidade:\n");
                                                    endereco.setCidade(endereco.nameNotNull(scanner.nextLine()));
                                                    Ferramentas.clearConsole();
                                                    System.out.println("\nDigite a UF:\n");
                                                    endereco.setUf(endereco.nameNotNull(scanner.nextLine()));
                                                    Ferramentas.clearConsole();

                                                    finalizar2 = true;
                                                } catch (NumberFormatException e) {
                                                    Ferramentas.clearConsole();
                                                    System.err.println(
                                                            "\nApenas números são permitidos para o campo Número!\n");
                                                    continue;
                                                } catch (NameNotNullOrInvalidException e) {
                                                    Ferramentas.clearConsole();
                                                    System.err.println("\nCampo nulo ou inválido! Tente novamente\n");
                                                    continue;
                                                }
                                            } while (!finalizar2);
                                        } else {
                                            Ferramentas.clearConsole();
                                            do {
                                                finalizar2 = false;
                                                try {
                                                    System.out.println("\nDigite o Número:\n");
                                                    String numString = scanner.nextLine();
                                                    int num = Integer.parseInt(numString);
                                                    endereco.setNumero(num);
                                                    finalizar2 = true;
                                                } catch (NumberFormatException e) {
                                                    Ferramentas.clearConsole();
                                                    System.err.println(
                                                            "\nApenas números são permitidos para o campo Número!\n");
                                                    continue;
                                                }

                                            } while (!finalizar2);
                                        }

                                        // Verifica se o endereço já existe e obtém o ID
                                        int idEndereco = enderecoDao.verificarExistenciaEObterId(
                                                endereco.cepF(endereco.getCep()),
                                                endereco.getLogradouro(),
                                                endereco.getNumero(), endereco.getBairro(), endereco.getCidade(),
                                                endereco.getUf());

                                        if (idEndereco != -1) {
                                            // Endereço já existe, usa o ID existente
                                            endereco = enderecoDao.getById(idEndereco);
                                            destino.setIdEndereco(endereco.getIdEndereco());
                                        } else {
                                            // Endereço não existe, insere no banco de dados e usa o novo ID
                                            enderecoDao.inserir(endereco);
                                            endereco = enderecoDao.getLast();
                                            destino.setIdEndereco(endereco.getIdEndereco());
                                        }

                                        try {
                                            destinoDao.verificarExistencia(destino.getNome());
                                            destinoDao.inserir(destino);
                                            destino = destinoDao.getLast();
                                            passeio.setIdDestino(destino.getIdDestino());
                                        } catch (RegistroJaExistenteException e) {
                                            Ferramentas.clearConsole();
                                            System.err.println("Registro já existente, tente novamente.");
                                        }
                                    }

                                    Ferramentas.clearConsole();

                                    Onibus onibus = new Onibus(0, null, null, null, null);

                                    do {
                                        System.out
                                                .println(
                                                        "\nDeseja Cadastrar o Ônibus do passeio agora ou mais tarde?\n\n1. Agora\n2. Mais tarde\n");
                                        opcoes2 = -1;
                                        String opcoes2Str = scanner.nextLine();
                                        Ferramentas.clearConsole();
                                        try {

                                            opcoes2 = Integer.parseInt(opcoes2Str);

                                        } catch (NumberFormatException e) {
                                            Ferramentas.clearConsole();
                                            System.err.println(
                                                    "\nApenas números são permitidos para o campo Número!\n");
                                        }
                                    } while (opcoes2 != 1 && opcoes2 != 2);

                                    Ferramentas.clearConsole();

                                    OnibusDao onibusDao = new OnibusDao();
                                    int escolha2 = -1;

                                    if (!onibusDao.getAll().isEmpty() && opcoes2 != 2) {
                                        System.out
                                                .println(
                                                        "\nDeseja Cadastrar o Ônibus um ônibus do banco de dados ou novo?\n\n1. Do banco\n2. Novo\n");
                                        String escolha2Str = scanner.nextLine();

                                        try {

                                            escolha2 = Integer.parseInt(escolha2Str);

                                        } catch (NumberFormatException e) {
                                            Ferramentas.clearConsole();
                                            System.err.println(
                                                    "\nApenas números são permitidos para o campo Número!\n");
                                        }
                                    }

                                    if (opcoes2 == 1 && escolha2 == 2) {

                                        do {
                                            finalizar2 = false;

                                            System.out.println("\nDigite a placa do ônibus\n");
                                            try {
                                                onibus.setPlaca(onibus.nameNotNull(scanner.nextLine()));
                                                finalizar2 = true;
                                            } catch (NameNotNullOrInvalidException e) {
                                                Ferramentas.clearConsole();
                                                System.err.println("\nCampo nulo ou inválido! Tente novamente\n");
                                            }
                                        } while (!finalizar2);

                                        Ferramentas.clearConsole();

                                        do {
                                            finalizar2 = false;

                                            System.out.println("\nDigite o modelo do ônibus\n");
                                            try {
                                                onibus.setModelo(onibus.nameNotNull(scanner.nextLine()));
                                                finalizar2 = true;
                                            } catch (NameNotNullOrInvalidException e) {
                                                Ferramentas.clearConsole();
                                                System.err.println("\nCampo nulo ou inválido! Tente novamente\n");
                                            }
                                        } while (!finalizar2);

                                        Ferramentas.clearConsole();

                                        do {
                                            finalizar2 = false;

                                            System.out.println("\nDigite o tipo de ônibus\n");
                                            try {
                                                onibus.setTipoOnibus(onibus.nameNotNull(scanner.nextLine()));
                                                finalizar2 = true;
                                            } catch (NameNotNullOrInvalidException e) {
                                                Ferramentas.clearConsole();
                                                System.err.println("\nCampo nulo ou inválido! Tente novamente\n");
                                            }
                                        } while (!finalizar2);

                                        Ferramentas.clearConsole();
                                        do {
                                            finalizar2 = false;

                                            System.out.println("\nDigite a empresa do ônibus\n");
                                            try {
                                                onibus.setEmpresa(onibus.nameNotNull(scanner.nextLine()));
                                                finalizar2 = true;
                                            } catch (NameNotNullOrInvalidException e) {
                                                Ferramentas.clearConsole();
                                                System.err.println("\nCampo nulo ou inválido! Tente novamente\n");
                                            }
                                        } while (!finalizar2);

                                        Ferramentas.clearConsole();
                                        try {
                                            onibusDao.verificarExistencia(onibus.getPlaca());
                                            onibusDao.inserir(onibus);
                                            onibus = onibusDao.getLast();
                                            passeio.setIdOnibus(onibus.getIdOnibus());
                                        } catch (RegistroJaExistenteException e) {
                                            Ferramentas.clearConsole();
                                            System.err.println("Registro já existente, tente novamente.");
                                        }

                                    }

                                    if (escolha2 == 1) {
                                        Ferramentas.clearConsole();

                                        try {
                                            List<Onibus> onibusList = onibusDao.getAll();
                                            for (Onibus o : onibusList) {
                                                System.out.println(o);
                                                System.out.println(
                                                        "\n-----------------------------------------------------\n");
                                            }

                                            System.out.println(
                                                    "\nDigite a id do Ônibus\n");
                                            String idStr = scanner.nextLine();
                                            int id = Integer.parseInt(idStr);
                                            Ferramentas.clearConsole();
                                            if (onibusDao.getById(id) != null) {
                                                onibus = onibusDao.getById(id);
                                                passeio.setIdOnibus(onibus.getIdOnibus());
                                                finalizar2 = true;
                                            }
                                        } catch (NumberFormatException e) {
                                            Ferramentas.clearConsole();
                                            System.err.println("\nInválido, digite um id válido\n");
                                        }

                                        Ferramentas.clearConsole();
                                    }
                                    PasseioDao passeioDao = new PasseioDao();
                                    passeioDao.inserir(passeio);
                                    passeio = passeioDao.getLast();
                                    do {
                                        finalizar2 = false;

                                        System.out.println(passeio);
                                        System.out.println(destino);
                                        if (onibus.getIdOnibus() != 0) {
                                            System.out.println(onibus);
                                        } else {
                                            System.out.println("\nAtenção!\nOnibus ainda a ser cadastrado");
                                        }
                                        System.out.println("\nEstá correto?\n1. Sim\n2. Não\n");

                                        String escolha2Str = scanner.nextLine();

                                        try {

                                            escolha2 = Integer.parseInt(escolha2Str);

                                        } catch (NumberFormatException e) {
                                            Ferramentas.clearConsole();
                                            System.err.println(
                                                    "\nApenas números são permitidos para o campo Número!\n");
                                        }

                                    } while (escolha2 == 1 && escolha2 == 2);

                                    if (escolha2 == 1) {
                                        System.out.println("\nConcluido! Aperte enter para continuar\n");
                                        scanner.nextLine();
                                        finalizar = true;
                                    }

                                    Ferramentas.clearConsole();

                                } while (!finalizar);

                                // Fim de Agendar Passeio

                                break;

                            case 2:

                                break;

                            case 3:

                                break;

                            case 4:

                                break;

                            case 5:

                                break;

                            case 6:

                                Pagamento pagamento = new Pagamento(0, 0, 0, false);
                                boolean finalizar2 = false;

                                Cliente cliente = new Cliente(null, null, null, null, 0, null, 0);
                                ClienteDao clienteDao = new ClienteDao();

                                int saida = 0;

                                if (!clienteDao.getAll().isEmpty()) {
                                    do {
                                        try {
                                            System.out.println("\nDigite o id do Cliente\n");
                                            String idStr = scanner.nextLine();
                                            int id = Integer.parseInt(idStr);
                                            cliente = clienteDao.getById(id);

                                            if (cliente != null) {
                                                System.out.println("\n" + cliente);
                                                System.out.println(
                                                        "\n-----------------------------------------------------\n");

                                                pagamento.setIdCliente(cliente.getIdCliente());

                                                System.out.println("\nAperte enter para sair\n");
                                                scanner.nextLine();
                                                Ferramentas.clearConsole();
                                                saida = 1;
                                                finalizar2 = true;

                                            } else {
                                                Ferramentas.clearConsole();
                                                System.err.println("\nCliente não encontrado.\n");
                                                saida = 0;
                                            }
                                        } catch (NumberFormatException e) {
                                            Ferramentas.clearConsole();
                                            System.err.println("\nInválido, digite um número válido!\n");
                                        }
                                    } while (!finalizar2);
                                } else {
                                    Ferramentas.clearConsole();
                                    System.err.println(
                                            "\nNenhum cliente cadastrado no banco de dados.\n\nAperte enter para sair\n");
                                    scanner.nextLine();
                                }

                                finalizar2 = false;

                                Passeio passeio = new Passeio(0, 0, 0, 0, null, null);
                                PasseioDao passeioDao = new PasseioDao();

                                if (!passeioDao.getAll().isEmpty() && !clienteDao.getAll().isEmpty()) {
                                    do {
                                        try {
                                            System.out.println("\nDigite o id do Passeio\n");
                                            String idStr = scanner.nextLine();
                                            int id = Integer.parseInt(idStr);
                                            passeio = passeioDao.getById(id);

                                            if (passeio != null) {
                                                System.out.println("\n" + passeio);
                                                DestinoDao destinoDao = new DestinoDao();
                                                Destino destino = destinoDao.getById(passeio.getIdDestino());
                                                System.out.println(destino);

                                                System.out.println(
                                                        "\n-----------------------------------------------------\n");

                                                pagamento.setIdPasseio(passeio.getIdPasseio());

                                                System.out.println("\nAperte enter para sair\n");
                                                scanner.nextLine();
                                                Ferramentas.clearConsole();
                                                finalizar2 = true;

                                            } else {
                                                Ferramentas.clearConsole();
                                                System.err.println("\nPasseio não encontrado.\n");
                                                saida = 0;
                                            }
                                        } catch (NumberFormatException e) {
                                            Ferramentas.clearConsole();
                                            System.err.println("\nInválido, digite um número válido!\n");
                                        }
                                    } while (!finalizar2);
                                } else {
                                    Ferramentas.clearConsole();
                                    System.err.println(
                                            "\nNenhum passeio cadastrado no banco de dados.\n\nAperte enter para sair\n");
                                    scanner.nextLine();
                                }

                                if (saida == 1) {
                                    PagamentoDao pagamentoDao = new PagamentoDao();

                                    int idPagamento = pagamentoDao.verificarExistenciaEObterId(pagamento.getIdCliente(),
                                            pagamento.getIdPasseio());

                                    if (idPagamento != -1) {
                                        pagamento = pagamentoDao.getById(idPagamento);
                                    } else {
                                        pagamentoDao.inserir(pagamento);
                                        pagamento = pagamentoDao.getLast();
                                    }

                                    System.out.println("\n" + pagamento);
                                    System.out.println(cliente);
                                    System.out.println(passeio);
                                    System.out.println(
                                            "\n-----------------------------------------------------\n");
                                    System.out.println("\nAperte enter para sair\n");
                                    scanner.nextLine();
                                    Ferramentas.clearConsole();

                                }

                                break;

                        }

                        break;
                    case 3:
                        // Opções Pagamento
                        Ferramentas.clearConsole();
                        boolean finalizar = false;
                        do {
                            do {
                                System.out.println(
                                        "\n1. Realizar Pagamento\n2. Buscar pagamento por id\n3. Listar pagamentos\n0. Voltar\n");
                                System.out.println("\nDigite uma opção\n");
                                String opcoesStr = scanner.nextLine();
                                try {
                                    opcoes = Integer.parseInt(opcoesStr);
                                    Ferramentas.clearConsole();
                                } catch (NumberFormatException e) {
                                    Ferramentas.clearConsole();
                                    System.err.println("\nInválido, digite um número válido!");
                                }
                            } while (opcoes != 0 && opcoes != 1 && opcoes != 2 && opcoes != 3);

                            switch (opcoes) {
                                case 1:
                                    Ferramentas.clearConsole();
                                    boolean finalizar2 = false;
                                    do {
                                        try {
                                            System.out.println(
                                                    "\nDigite a id do Pagamento\n");
                                            String idStr = scanner.nextLine();
                                            int id = Integer.parseInt(idStr);
                                            Ferramentas.clearConsole();
                                            Pagamento pagamento = new Pagamento(0, 0, 0, false);
                                            PagamentoDao pagamentoDao = new PagamentoDao();
                                            pagamento = pagamentoDao.getById(id);
                                            if (pagamentoDao.getById(id) != null) {
                                                pagamento.setSituacao(true);
                                                pagamentoDao.updateSituacao(pagamento, id);
                                                Cliente cliente = new Cliente(idStr, idStr, idStr, null, id, idStr, id);
                                                Passeio passeio = new Passeio(id, id, id, id, null, null);
                                                ClienteDao clienteDao = new ClienteDao();
                                                PasseioDao passeioDao = new PasseioDao();
                                                cliente = clienteDao.getById(pagamento.getIdCliente());
                                                passeio = passeioDao.getById(pagamento.getIdPasseio());

                                                Ferramentas.clearConsole();
                                                System.out.println("\n" + pagamento);
                                                System.out.println(cliente);
                                                System.out.println(passeio + "\n");
                                                System.out.println("\nAperte enter para continuar\n");
                                                scanner.nextLine();
                                                Ferramentas.clearConsole();
                                                finalizar2 = true;
                                            } else {
                                                Ferramentas.clearConsole();
                                                System.err.println("\nPagamento não encontrado.\n");
                                                finalizar2 = true;
                                            }
                                        } catch (NumberFormatException e) {
                                            Ferramentas.clearConsole();
                                            System.err.println("\nInválido, digite um número válido!\n");
                                        }

                                    } while (!finalizar2);

                                    break;

                                case 2:
                                    Ferramentas.clearConsole();
                                    finalizar2 = false;
                                    do {
                                        try {
                                            System.out.println(
                                                    "\nDigite a id do Pagamento\n");
                                            String idStr = scanner.nextLine();
                                            int id = Integer.parseInt(idStr);
                                            Ferramentas.clearConsole();
                                            Pagamento pagamento = new Pagamento(0, 0, 0, false);
                                            PagamentoDao pagamentoDao = new PagamentoDao();
                                            pagamento = pagamentoDao.getByIdCliente(id);
                                            if (pagamentoDao.getByIdCliente(id) != null) {
                                                Ferramentas.clearConsole();
                                                System.out.println("\n" + pagamento);
                                                Cliente cliente = new Cliente(idStr, idStr, idStr, null, id, idStr, id);
                                                ClienteDao clienteDao = new ClienteDao();
                                                cliente = clienteDao.getById(pagamento.getIdCliente());
                                                System.out.println("\n" + cliente);
                                                Passeio passeio = new Passeio(id, id, id, id, null, null);
                                                PasseioDao passeioDao = new PasseioDao();
                                                passeio = passeioDao.getById(pagamento.getIdPasseio());
                                                System.out.println("\n" + passeio);
                                                System.out.println("\nAperte enter para continuar\n");
                                                scanner.nextLine();
                                                Ferramentas.clearConsole();
                                                finalizar2 = true;
                                            } else {
                                                Ferramentas.clearConsole();
                                                System.err.println("\nPagamento não existe\n");
                                                finalizar2 = true;
                                            }
                                        } catch (NumberFormatException e) {
                                            Ferramentas.clearConsole();
                                            System.err.println("\nInválido, digite um número válido!\n");
                                        } catch (TelefoneInvalidoException e) {
                                            Ferramentas.clearConsole();
                                            System.err.println("\nTelefone inválido!\n\nTente novamente\n");
                                        }
                                    } while (!finalizar2);

                                    break;

                                case 3:
                                    finalizar2 = false;
                                    do {

                                        do {
                                            System.out.println(
                                                    "\n1. Listar pagamentos de um cliente\n2. Listar pagamentos de um Passeio\n");
                                            System.out.println("\nDigite uma opção\n");
                                            String opcoesStr = scanner.nextLine();
                                            try {
                                                opcoes = Integer.parseInt(opcoesStr);
                                                Ferramentas.clearConsole();
                                            } catch (NumberFormatException e) {
                                                Ferramentas.clearConsole();
                                                System.err.println("\nInválido, digite um número válido!");
                                            }
                                        } while (opcoes != 1 && opcoes != 2);

                                        if (opcoes == 1) {
                                            boolean finalizar3 = false;
                                            do {
                                                try {
                                                    System.out.println("\nDigite a id do Cliente\n");
                                                    String idStr = scanner.nextLine();
                                                    int id = Integer.parseInt(idStr);

                                                    PagamentoDao pagamentoDao = new PagamentoDao();
                                                    ClienteDao clienteDao = new ClienteDao();
                                                    PasseioDao passeioDao = new PasseioDao();
                                                    List<Pagamento> pagamentos = pagamentoDao.getByCliente(id);
                                                    Ferramentas.clearConsole();
                                                    for (Pagamento p : pagamentos) {
                                                        Cliente cliente = clienteDao.getById(p.getIdCliente());
                                                        Passeio passeio = passeioDao.getById(p.getIdPasseio());
                                                        System.out.println("\n" + cliente);
                                                        System.out.println(p);
                                                        System.out.println(passeio + "\n");
                                                        System.out.println(
                                                                "\n-----------------------------------------------------\n");
                                                    }

                                                    System.out.println("\nAperte enter para sair\n");
                                                    scanner.nextLine();
                                                    finalizar3 = true;

                                                } catch (ElementoNaoEncontradoExption e) {
                                                    Ferramentas.clearConsole();
                                                    System.out.println("\nNenhum cliente no banco de dados\n");

                                                } catch (NumberFormatException e) {
                                                    Ferramentas.clearConsole();
                                                    System.err.println("\nInválido, digite um número válido!");

                                                }
                                            } while (finalizar3);

                                        } else {
                                            boolean finalizar3 = false;
                                            do {
                                                try {
                                                    System.out.println("\nDigite a id do Passeio\n");
                                                    String idStr = scanner.nextLine();
                                                    int id = Integer.parseInt(idStr);

                                                    PagamentoDao pagamentoDao = new PagamentoDao();
                                                    ClienteDao clienteDao = new ClienteDao();
                                                    PasseioDao passeioDao = new PasseioDao();
                                                    List<Pagamento> pagamentos = pagamentoDao.getByPasseio(id);
                                                    Ferramentas.clearConsole();
                                                    for (Pagamento p : pagamentos) {
                                                        Cliente cliente = clienteDao.getById(p.getIdCliente());
                                                        Passeio passeio = passeioDao.getById(p.getIdPasseio());
                                                        System.out.println("\n" + passeio);
                                                        System.out.println(p);
                                                        System.out.println(cliente + "\n");
                                                        System.out.println(
                                                                "\n-----------------------------------------------------\n");
                                                    }

                                                    System.out.println("\nAperte enter para sair\n");
                                                    scanner.nextLine();
                                                    finalizar3 = true;

                                                } catch (ElementoNaoEncontradoExption e) {
                                                    Ferramentas.clearConsole();
                                                    System.out.println("\nNenhum cliente no banco de dados\n");

                                                } catch (NumberFormatException e) {
                                                    Ferramentas.clearConsole();
                                                    System.err.println("\nInválido, digite um número válido!");

                                                }
                                            } while (finalizar3);
                                        }

                                    } while (!finalizar2);
                                    break;
                            }
                            Ferramentas.clearConsole();

                        } while (!finalizar);
                        break;
                }

            } while (opcoes != 0);

            System.out.println("\nTUDO PRONTO!!");
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
