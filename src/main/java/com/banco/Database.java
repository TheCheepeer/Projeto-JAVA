package com.banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.tests.testDb;

public class Database {
    private static Database INSTANCE = null;

    private Connection connection = null;

    private Database() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite::resource:" +
                    testDb.class.getResource("/res/database.db"));
        } catch (SQLException e) {
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
