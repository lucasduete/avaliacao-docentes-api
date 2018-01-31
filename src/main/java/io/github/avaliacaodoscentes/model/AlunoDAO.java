package io.github.avaliacaodoscentes.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AlunoDAO {

    private Connection conn;

    public AlunoDAO() {
        try {
            conn = Conexao.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public boolean cadastrarAluno(Aluno aluno) {

        String sql = "INSERT INTO Aluno(Nome, Senha, Matricula, emailAdministrador, codCurso) VALUES (?,?,?,?,?);";

        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getSenha());
            stmt.setString(3, aluno.getMatricula());
            stmt.setString(4, aluno.getEmailAdministrador());
            stmt.setInt(5, aluno.getCodCurso());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean loginAluno(String matricula, String senha) {

        String sql = "SELECT * FROM Aluno WHERE Matricula = ? AND Senha = ?";

        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, matricula);
            stmt.setString(2, senha);

            return stmt.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
}
