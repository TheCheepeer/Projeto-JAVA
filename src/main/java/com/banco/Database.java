package com.banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static Database INSTANCE = null;

    private Connection connection = null;

    private Database() {
        try {
            connection = DriverManager
                    .getConnection("jdbc:sqlite:resources/database.db");

            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30); // set timeout to 30 sec.

            String sql = FileUtils.loadTextFile("turismo.sql");
            statement.executeUpdate(sql);

        } catch (Exception e) {
            System.err.println("Houve um problema ao Criar o arquivo do banco.");
            e.printStackTrace();
        }
    }

    public static Database getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Database();
        }

        return INSTANCE;
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            System.err.println("Houve um erro ao fechar a conexão com o banco.");
            e.printStackTrace();
        }
    }
}
