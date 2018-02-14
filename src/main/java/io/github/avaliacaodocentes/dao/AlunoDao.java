package io.github.avaliacaodocentes.dao;

import io.github.avaliacaodocentes.exceptions.CredenciaisInvalidasException;
import io.github.avaliacaodocentes.model.Aluno;
import io.github.avaliacaodocentes.factory.Conexao;
import io.github.avaliacaodocentes.resources.Encryption;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlunoDao {

    private Connection conn;

    public AlunoDao() throws SQLException, ClassNotFoundException{
        conn = Conexao.getConnection();
    }

    public boolean cadastrar(Aluno aluno) {

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

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public Aluno buscar(String matricula) {

        Aluno aluno = null;

        String sql = "SELECT * FROM Aluno WHERE Matricula ILIKE ?";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, matricula);

            ResultSet rs = stmt.executeQuery();

            while(rs.next())
                aluno = new Aluno(
                        rs.getString("Nome"),
                        rs.getString("Senha"),
                        rs.getString("Matricula"),
                        rs.getString("EmailAdministrador"),
                        rs.getInt("CodCurso")
                );

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }

        return aluno;
    }

    public boolean editar(Aluno aluno) {

        String sql = "UPDATE Aluno SET nome = ?, senha = ?, matricula = ?, emailAdministrador = ?, codCurso = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, Encryption.encrypt(aluno.getSenha()));
            stmt.setString(3, aluno.getMatricula());
            stmt.setString(4, aluno.getEmailAdministrador());
            stmt.setInt(5, aluno.getCodCurso());

            stmt.executeUpdate();

            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean remover(String matricula) {

        String sql = "DELETE FROM Avaliacao_Aluno_Professor WHERE MatAluno ILIKE ?;" +
                "DELETE FROM Aluno WHERE Matricula ILIKE ?;";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, matricula);
            stmt.setString(2, matricula);

            stmt.executeUpdate();

            stmt.close();
            conn.close();
        } catch (SQLException ex) {

            ex.printStackTrace();
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
