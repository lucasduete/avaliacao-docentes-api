package io.github.avaliacaodocentes.dao.interfaces;

import io.github.avaliacaodocentes.exceptions.CredenciaisInvalidasException;
import io.github.avaliacaodocentes.model.Aluno;

import java.sql.SQLException;

public interface AlunoDaoInterface {

    public boolean cadastrar(Aluno aluno);
    public Aluno buscar(String matricula);
    public boolean editar(Aluno aluno);
    public boolean remover(String matricula);
    public Aluno login(String matricula, String senha) throws CredenciaisInvalidasException, SQLException;
}
