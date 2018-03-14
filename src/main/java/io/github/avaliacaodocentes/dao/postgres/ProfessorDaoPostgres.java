package io.github.avaliacaodocentes.dao.postgres;

import io.github.avaliacaodocentes.dao.interfaces.ProfessorDaoInterface;
import io.github.avaliacaodocentes.factory.Conexao;
import io.github.avaliacaodocentes.model.Professor;
import io.github.avaliacaodocentes.resources.Encryption;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProfessorDaoPostgres implements ProfessorDaoInterface {

    private Connection conn;

    public ProfessorDaoPostgres() throws SQLException, ClassNotFoundException {
        conn = Conexao.getConnection();
    }

    public boolean cadastrar(Professor professor) {

        String sql = "INSERT INTO Professor(Nome, Matricula, Nota, EmailAdministrador) " +
                "VALUES (?,?,?,?);";

        try {

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getMatricula());
            stmt.setFloat(3, professor.getNota());
            stmt.setString(4, professor.getEmailAdministrador());

            stmt.executeUpdate();

            stmt.close();
            conn.close();
        } catch (SQLException ex) {

            ex.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean editar(Professor professor) {

        String sql = "UPDATE Professor SET Nome = ?, " +
                "EmailAdministrador = ? WHERE Matricula ILIKE ?;";

        try {

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getEmailAdministrador());
            stmt.setString(3, professor.getMatricula());

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

        String sql = "DELETE FROM Professor WHERE Matricula ILIKE ?;";

        try {

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, matricula);

            stmt.executeUpdate();

            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }
    
    public List<Professor> listarTodos() {
        
        List<Professor> professores = new ArrayList<>();
        String sql = "SELECT Matricula,Nome,Nota FROM PROFESSOR ORDER BY Nome ASC";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()) {
                Professor professor = new Professor();

                professor.setMatricula(rs.getString("Matricula"));
                professor.setNome(rs.getString("Nome"));
                professor.setNota(rs.getFloat("Nota"));

                professores.add(professor);
            }
            
            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return professores;
    }
    
    public List<Professor> listarPorCurso(int codCurso) {
        
        List<Professor> lista = new ArrayList<>();
        String sql = "SELECT * FROM PROFESSOR_CURSO WHERE Codigo = ? ORDER BY Nome ASC";

        return null;
    }
}
