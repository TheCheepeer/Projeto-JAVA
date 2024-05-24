package com.banco;

import java.sql.Connection;

public class EnderecoDao {
    private Connection con;

    public EnderecoDao() {
        con = Database.getInstance().getConnection();
    }
}
