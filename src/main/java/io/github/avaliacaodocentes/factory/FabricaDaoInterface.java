package io.github.avaliacaodocentes.factory;

import io.github.avaliacaodocentes.dao.interfaces.*;

import java.sql.SQLException;

public interface FabricaDaoInterface {

    public AdministradorDaoInterface criarAdministradorDao() throws SQLException, ClassNotFoundException;
    public AlunoDaoInterface criarAlunoDao() throws SQLException, ClassNotFoundException;
    public AvaliacaoDaoInterface criarAvaliacaoDao() throws SQLException, ClassNotFoundException;
    public CriterioDaoInterface criarCriterioDao() throws SQLException, ClassNotFoundException;
    public CursoDaoInterface criarCursoDao() throws SQLException, ClassNotFoundException;
    public ProfessorDaoInterface criarProfessorDao() throws SQLException, ClassNotFoundException;
}
