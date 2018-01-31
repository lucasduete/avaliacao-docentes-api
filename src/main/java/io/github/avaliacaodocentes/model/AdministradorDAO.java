package io.github.avaliacaodocentes.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdministradorDAO {

    private Connection conn;

    public AdministradorDAO() {
        try {
            conn = Conexao.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public boolean cadastrarAdmin(Administrador admin) {

        String sql = "INSERT INTO Administrador(Email, Nome, Senha) VALUES (?,?,?);";

        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, admin.getEmail());
            stmt.setString(2, admin.getNome());
            stmt.setString(3, admin.getSenha());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean loginAdmin(String email, String senha) {

        String sql = "SELECT * FROM Administrador WHERE Email = ? AND Senha = ?";

        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, email);
            stmt.setString(2, senha);

            return stmt.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
}
