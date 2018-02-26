package io.github.avaliacaodocentes.dao.postgres;

import io.github.avaliacaodocentes.dao.interfaces.AdministradorDaoInterface;
import io.github.avaliacaodocentes.exceptions.CredenciaisInvalidasException;
import io.github.avaliacaodocentes.model.Administrador;
import io.github.avaliacaodocentes.factory.Conexao;
import io.github.avaliacaodocentes.resources.Encryption;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdministradorDaoPostgres implements AdministradorDaoInterface {

    private Connection conn;

    public AdministradorDaoPostgres() throws SQLException, ClassNotFoundException {
        conn = Conexao.getConnection();
    }

    public boolean cadastrar(Administrador admin) {

        String sql = "INSERT INTO Administrador(Email, Nome, Senha) VALUES (?,?,?);";

        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, admin.getEmail());
            stmt.setString(2, admin.getNome());

            //Encripta a senha antes de salvar
            stmt.setString(3, Encryption.encrypt(admin.getSenha()));

            stmt.executeUpdate();

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public Administrador buscar(String email) {

        Administrador admin = null;

        String sql = "SELECT * FROM Administrador WHERE Email ILIKE ?";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();

            while(rs.next())
                admin = new Administrador(
                        rs.getString("Email"),
                        rs.getString("Nome"),
                        rs.getString("Senha")
                );

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }

        return admin;
    }

    public boolean editar(Administrador administrador) {

        String sql = "UPDATE Administrador SET Email = ?, Nome = ?, Senha = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, administrador.getEmail());
            stmt.setString(2, administrador.getNome());;
            stmt.setString(3, Encryption.encrypt(administrador.getSenha()));

            stmt.executeUpdate();

            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }

    public Administrador login(String email, String senha) throws CredenciaisInvalidasException, SQLException {

        Administrador admin = null;

        String sql = "SELECT Senha FROM Administrador WHERE Email ILIKE ?";
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
