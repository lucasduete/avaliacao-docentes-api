package io.github.avaliacaodocentes.dao;

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

public class ProfessorDao {

    private Connection conn;

    public ProfessorDao() throws SQLException, ClassNotFoundException{
        conn = Conexao.getConnection();
    }

    public boolean cadastrar(Professor professor) {

        String sql_1 = "INSERT INTO Professor(Nome, Senha, Matricula, Nota, EmailAdministrador) " +
                "VALUES (?,?,?,?,?)";
        String
        try {

            PreparedStatement stmt = conn.prepareStatement(sql_1);

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

    public boolean editar(Professor professor) {

        String sql = "UPDATE Professor SET Nome = ?, Senha = ?, Nota = ?, " +
                "EmailAdministrador = ? WHERE Matricula ILIKE ?;";

        try {

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, professor.getNome());
            stmt.setString(2, Encryption.encrypt(professor.getSenha()));
            stmt.setFloat(3, professor.getNota());
            stmt.setString(4, professor.getEmailAdministrador());
            stmt.setString(5, professor.getMatricula());

            stmt.executeUpdate();

            stmt.close();
            conn.close();
        } catch (SQLException ex) {

            ex.printStackTrace();
            return false;
        }

        return true;
    }
    
    public List<Professor> listarTodos(){
        
        List<Professor> lista = new ArrayList<>();
        String sql = "SELECT * FROM PROFESSOR";
        
        
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet result = stmt.executeQuery();
            
            while(result.next()){
                Professor p = new Professor();
                p.setEmailAdministrador(result.getString("emailAdministrador"));
                p.setMatricula(result.getString("matricula"));
                p.setNome(result.getString("nome"));
                p.setSenha(result.getString("senha"));
                lista.add(p);
            }
            
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ProfessorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<Professor> listarPorCurso(int codCurso){
        
        List<Professor> lista = new ArrayList<>();
        String sql = "SELECT * FROM PROFESSOR_CURSO WHERE Codigo = ?";

        return null;
    }
}
