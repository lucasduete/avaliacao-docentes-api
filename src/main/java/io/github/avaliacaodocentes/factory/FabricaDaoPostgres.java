package io.github.avaliacaodocentes.factory;

import io.github.avaliacaodocentes.dao.interfaces.*;
import io.github.avaliacaodocentes.dao.postgres.*;

import java.sql.SQLException;

public class FabricaDaoPostgres implements FabricaDaoInterface {

    @Override
    public AdministradorDaoInterface criarAdministradorDao() throws SQLException, ClassNotFoundException {
        return new AdministradorDaoPostgres();
    }

    @Override
    public AlunoDaoInterface criarAlunoDao() throws SQLException, ClassNotFoundException {
        return new AlunoDaoPostgres();
    }

    @Override
    public AvaliacaoDaoInterface criarAvaliacaoDao() throws SQLException, ClassNotFoundException {
        return new AvaliacaoDaoPostgres();
    }

    @Override
    public CriterioDaoInterface criarCriterioDao() throws SQLException, ClassNotFoundException {
        return new CriterioDaoPostgres();
    }

    @Override
    public CursoDaoInterface criarCursoDao() throws SQLException, ClassNotFoundException {
        return new CursoDaoPostgres();
    }

    @Override
    public ProfessorDaoInterface criarProfessorDao() throws SQLException, ClassNotFoundException {
        return new ProfessorDaoPostgres();
    }

    @Override
    public RankingDaoInterface criarRankingDao() throws SQLException, ClassNotFoundException {
        return new RankingDaoPostgres();
    }

}
