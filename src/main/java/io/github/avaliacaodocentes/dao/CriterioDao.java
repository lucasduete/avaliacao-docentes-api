package io.github.avaliacaodocentes.dao;

import io.github.avaliacaodocentes.factory.Conexao;
import io.github.avaliacaodocentes.model.Criterio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public boolean editar(Criterio criterio) {

        String sql = "UPDATE Criterio SET Ponto_Avaliativo = ?, EmailAdministrador = ? " +
                "WHERE Codigo = ?;";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, criterio.getPontoAvaliativo());
            stmt.setString(2, criterio.getEmailAdministrador());
            stmt.setInt(3, criterio.getCodigo());

            stmt.executeUpdate();

            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }
    
    public List<Criterio> listar() {
        
        String sql = "SELECT * FROM CRITERIO";
        List<Criterio> lista = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet result = stmt.executeQuery();

            while(result.next()) {
                Criterio criterio = new Criterio();

                criterio.setCodigo(result.getInt("codigo"));
                criterio.setEmailAdministrador(result.getString("emailadministrador"));
                criterio.setPontoAvaliativo(result.getString("ponto_avaliativo"));

                lista.add(criterio);
            }

            result.close();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }

        return lista;
    }
}
