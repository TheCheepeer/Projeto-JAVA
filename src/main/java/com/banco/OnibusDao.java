package com.banco;

import java.sql.Connection;

public class OnibusDao {
    private Connection con;

    public OnibusDao() {
        con = Database.getInstance().getConnection();
    }
}
