package io.github.avaliacaodocentes.dao;

import io.github.avaliacaodocentes.factory.Conexao;
import io.github.avaliacaodocentes.model.Criterio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CriterioDao {

    private Connection conn;

    public CriterioDao() throws SQLException, ClassNotFoundException {
        conn = Conexao.getConnection();
    }

    public boolean adicionar(Criterio criterio) {

        String sql = "INSERT INTO Criterio(Ponto_Avaliativo, EmailAdministrador) VALUES (?,?);";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, criterio.getPontoAvaliativo());
            stmt.setString(2, criterio.getEmailAdministrador());

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
