package com.banco;

import java.sql.Connection;

public class PagamentoDao {
    private Connection con;

    public PagamentoDao() {
        con = Database.getInstance().getConnection();
    }
}
