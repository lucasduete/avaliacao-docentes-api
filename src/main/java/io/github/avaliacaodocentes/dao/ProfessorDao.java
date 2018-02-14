package io.github.avaliacaodocentes.dao;

import io.github.avaliacaodocentes.factory.Conexao;
import io.github.avaliacaodocentes.model.Professor;
import io.github.avaliacaodocentes.resources.Encryption;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProfessorDao {

    private Connection conn;

    public ProfessorDao() throws SQLException, ClassNotFoundException{
        conn = Conexao.getConnection();
    }

    public boolean cadastrar(Professor professor) {

        String sql = "INSERT INTO Professor(Nome, Senha, Matricula, Nota, EmailAdministrador) " +
                "VALUES (?,?,?,?,?)";

        try {

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, professor.getNome());
            stmt.setString(2, Encryption.encrypt(professor.getSenha()));
            stmt.setString(3, professor.getMatricula());
            stmt.setFloat(4, professor.getNota());
            stmt.setString(5, professor.getEmailAdministrador());

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
