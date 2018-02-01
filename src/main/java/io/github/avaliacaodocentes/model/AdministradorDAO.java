package io.github.avaliacaodocentes.model;

import io.github.avaliacaodocentes.exceptions.CredenciaisInvalidasException;
import io.github.avaliacaodocentes.resources.Encryption;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public Administrador loginAdmin(String email, String senha) throws CredenciaisInvalidasException, SQLException {

        Administrador admin = null;

        String sql = "SELECT * FROM Administrador WHERE Email ILIKE ?";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, email);

        ResultSet rs = stmt.executeQuery();

        if(!rs.next())
            throw new CredenciaisInvalidasException("As Credenciais usadas no Login de Administrador sao Invalidas");

        if(Encryption.checkPassword(senha, rs.getString("Senha")))
            admin = buscar(email);
        else
            throw new CredenciaisInvalidasException("As Credenciais usadas no Login de Administrador sao Invalidas");

        rs.close();
        stmt.close();
        conn.close();

        return admin;
    }
}
