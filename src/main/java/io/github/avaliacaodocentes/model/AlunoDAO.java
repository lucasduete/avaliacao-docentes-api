package io.github.avaliacaodocentes.model;

import io.github.avaliacaodocentes.exceptions.CredenciaisInvalidasException;
import io.github.avaliacaodocentes.resources.Encryption;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

            //Encripta a senha antes de salvar
            stmt.setString(2, Encryption.encrypt(aluno.getSenha()));

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

    public Aluno loginAluno(String matricula, String senha) throws CredenciaisInvalidasException, SQLException {

        Aluno aluno = null;

        String sql = "SELECT Senha FROM Aluno WHERE Matricula ILIKE ?";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, matricula);

        ResultSet rs = stmt.executeQuery();

        if(!rs.next())
            throw new CredenciaisInvalidasException("As Credenciais usadas no Login de Aluno sao Invalidas");

        if(Encryption.checkPassword(senha, rs.getString("Senha")))
            aluno = buscar(matricula);
        else
            throw new CredenciaisInvalidasException("As Credenciais usadas no Login de Aluno sao Invalidas");

        rs.close();
        stmt.close();
        conn.close();

        return aluno;
    }
}
