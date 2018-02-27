package io.github.avaliacaodocentes.dao.postgres;

import io.github.avaliacaodocentes.dao.interfaces.RankingDaoInterface;
import io.github.avaliacaodocentes.factory.Conexao;
import io.github.avaliacaodocentes.model.Professor;

import javax.naming.ldap.PagedResultsControl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RankingDaoPostgres implements RankingDaoInterface {

    private final Connection conn;

    public RankingDaoPostgres() throws SQLException, ClassNotFoundException {
        conn = Conexao.getConnection();
    }

    @Override
    public List<Professor> gerarRanking() {
        String sql = "SELECT Nome, Nota FROM Professor ORDER BY Nota DESC";
        List<Professor> professores = new ArrayList<>();

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Professor professor = new Professor();

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

    @Override
    public List<Professor> gerarRankingSemestral(String semestre) {
        String sql = "SELECT Prof.Nome, AvSemestral.Nota FROM Professor AS Prof " +
                "JOIN Avaliacao_Semestral AS AvSemestral " +
                "ON Prof.Matricula = AvSemestral.MatProfessor " +
                "WHERE Semestre ILIKE ? ORDER BY AvSemestral.Nota DESC;";
        List<Professor> professores = new ArrayList<>();

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, semestre);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Professor professor = new Professor();

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

    public boolean finalizarSemestre(String semestre) {
        String sql = "SELECT Cur.Codigo AS CodCurso, Prof.matricula AS MatProfessor, Prof.Nota AS NotaProfessor FROM Curso AS Cur JOIN Professor_Curso AS Prof_Cur ON Cur.codigo = Prof_Cur.codcurso JOIN Professor AS Prof ON Prof_Cur.matprofessor = Prof.matricula;";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            String internalSQL = "INSERT INTO Avaliacao_Semestral(matprofessor, codcurso, semestre, nota) " +
                    "VALUES (?,?,?,?);";
            PreparedStatement internalStmt = conn.prepareStatement(internalSQL);

            while (rs.next()) {
                internalStmt.setString(1, rs.getString("MatProfessor"));
                internalStmt.setInt(2, rs.getInt("CodCurso"));
                internalStmt.setString(3, semestre);
                internalStmt.setFloat(4, rs.getFloat("NotaProfessor"));

                internalStmt.executeUpdate();
            }

            internalSQL = "DELETE FROM Criterio_Avaliacao; DELETE FROM Avaliacao_Aluno_Professor; DELETE FROM AVALIACAO;";
            internalStmt = conn.prepareStatement(internalSQL);

            internalStmt.executeUpdate();

            internalStmt.close();
            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }
}
