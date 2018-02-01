package io.github.avaliacaodocentes.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String url = "jdbc:postgresql://ec2-23-23-92-179.compute-1.amazonaws.com:5432/d29ih0eq9ac50k";
    private static final String usuario = "fyrfesfmxmlamk";
    private static final String senha = "22df713743820d272408aa6d72bee73c02f4f35f6d3e2be0223963604c66736c";
    
    public static Connection getConnection() throws SQLException, ClassNotFoundException{
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(url, usuario, senha);
    }

}
