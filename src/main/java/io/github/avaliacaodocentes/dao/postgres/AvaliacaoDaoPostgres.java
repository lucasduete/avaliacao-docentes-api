package io.github.avaliacaodocentes.dao.postgres;

import io.github.avaliacaodocentes.dao.interfaces.AvaliacaoDaoInterface;
import io.github.avaliacaodocentes.factory.Conexao;
import io.github.avaliacaodocentes.model.Avaliacao;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AvaliacaoDaoPostgres implements AvaliacaoDaoInterface {

    private final Connection conn;

    public AvaliacaoDaoPostgres() throws SQLException, ClassNotFoundException {
        conn = Conexao.getConnection();
    }

    public boolean cadastrar(Avaliacao avaliacao) {

        deletar(avaliacao.getMatAluno(), avaliacao.getMatProfessor());

        String sql = "INSERT INTO Avaliacao(Data, Comentario) VALUES (?,?) RETURNING Codigo;";
        int codAvaliacao = 0;

        PreparedStatement stmt;
        try {

            //Salva Avaliaçao
            stmt = conn.prepareStatement(sql);

            stmt.setDate(1, Date.valueOf(avaliacao.getData()));
            stmt.setString(2, avaliacao.getComentario());

            //Recupera o Id da Avalicao para Proximas Querys
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                codAvaliacao = rs.getInt("Codigo");
            }

            //Salva Avaliacao_Aluno_Professor
            sql = "INSERT INTO Avaliacao_Aluno_Professor(MatAluno, MatProfessor, CodAvaliacao) VALUES (?,?,?)";

            stmt = conn.prepareStatement(sql);

            stmt.setString(1, avaliacao.getMatAluno());
            stmt.setString(2, avaliacao.getMatProfessor());
            stmt.setInt(3, codAvaliacao);

            stmt.executeUpdate();

            //Para Cada Obj Pontuacao dentro de getPontuacoes em Avaliacao
            //salva ele na tabela correta
            sql = String.format("INSERT INTO CRITERIO_AVALIACAO (CodAvaliacao, CodCriterio, Pontuacao) "
                    + "VALUES (%d,?,?)", codAvaliacao);
            final PreparedStatement internalStmt = conn.prepareStatement(sql);
            final String aux = "";

            avaliacao.getPontuacoes().forEach(pontuacao -> {
                try {
                    internalStmt.setInt(1, pontuacao.getCodCriterio());
                    internalStmt.setDouble(2, pontuacao.getPontuacao());
                    internalStmt.execute();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    aux.concat(pontuacao + ";");
                }
            });

            if (!aux.isEmpty()) {
                System.out.printf("\n\nLista de Pontuaçoes com Erros: \n" + aux);
                return false;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean deletar(int codigo) {

        String sql = "DELETE FROM CRITERIO_AVALIACAO WHERE CodAvaliacao = ?; " +
                "DELETE FROM AVALIACAO_ALUNO_PROFESSOR WHERE CodAvaliacao = ?;" +
                "DELETE FROM Avaliacao WHERE Codigo = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, codigo);
            stmt.setInt(2, codigo);
            stmt.setInt(3, codigo);
            stmt.execute();

            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public void deletar(String matAluno, String matProfessor) {

        String sql = "DELETE FROM AVALIACAO_ALUNO_PROFESSOR WHERE  MatAluno ILIKE ? AND MatProfessor ILIKE ? RETURNING CodAvaliacao;";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, matAluno);
            stmt.setString(2, matProfessor);

            ResultSet rs = stmt.executeQuery();
            int codAvaliacao = -1;
            if (rs.next())
                codAvaliacao = rs.getInt("codAvaliacao");

            sql = "DELETE FROM CRITERIO_AVALIACAO WHERE codAvaliacao = ?; " +
                    "DELETE FROM Avaliacao WHERE Codigo = ?";

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, codAvaliacao);
            stmt.setInt(2, codAvaliacao);

            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    
    
}
