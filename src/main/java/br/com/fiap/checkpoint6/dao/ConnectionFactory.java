package br.com.fiap.checkpoint6.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String URL =
            "jdbc:oracle:thin:@//oracle.fiap.com.br:1521/ORCL";

    private static final String USUARIO = "RM571053";
    private static final String SENHA = "120407";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}

