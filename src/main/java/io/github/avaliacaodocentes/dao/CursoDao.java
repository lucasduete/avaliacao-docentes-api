package io.github.avaliacaodocentes.dao;

import io.github.avaliacaodocentes.factory.Conexao;
import io.github.avaliacaodocentes.model.Curso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CursoDao {

    private Connection conn;

    public CursoDao () throws SQLException, ClassNotFoundException{
        conn = Conexao.getConnection();
    }

    public boolean cadastrar(Curso curso) {

        String sql = "INSERT INTO Curso(Nome, Codigo, EmailAdministrador) VALUES (?,?,?);";

        try {

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, curso.getNome());
            stmt.setInt(2, curso.getCodigo());
            stmt.setString(3, curso.getEmailAdministrador());

            stmt.executeUpdate();

            stmt.close();
            conn.close();
        } catch (SQLException ex) {

            ex.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean editar(Curso curso, int codigo) {

        String sql = "UPDATE Curso SET Nome = ?, Codigo = ?, EmailAdministrador = ? WHERE Codigo = ?;";

        try {

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, curso.getNome());
            stmt.setInt(2, curso.getCodigo());
            stmt.setString(3, curso.getEmailAdministrador());
            stmt.setInt(4, codigo);

            stmt.executeUpdate();

            stmt.close();
            conn.close();
        } catch (SQLException ex) {

            ex.printStackTrace();
            return false;
        }

        return true;
    }
}
