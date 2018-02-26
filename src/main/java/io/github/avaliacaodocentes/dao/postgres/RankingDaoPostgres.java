package io.github.avaliacaodocentes.dao.postgres;

import io.github.avaliacaodocentes.dao.interfaces.RankingDaoInterface;
import io.github.avaliacaodocentes.factory.Conexao;
import io.github.avaliacaodocentes.model.Professor;

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
}
