package com.banco;

import java.sql.Connection;

public class DestinoDao {
    private Connection con;

    public DestinoDao() {
        con = Database.getInstance().getConnection();
    }
}
