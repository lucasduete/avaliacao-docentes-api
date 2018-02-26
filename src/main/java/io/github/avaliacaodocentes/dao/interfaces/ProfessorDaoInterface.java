package io.github.avaliacaodocentes.dao.interfaces;

import io.github.avaliacaodocentes.model.Professor;

import java.util.List;

public interface ProfessorDaoInterface {

    public boolean cadastrar(Professor professor);
    public boolean editar(Professor professor);
    public List<Professor> listarTodos();
    public List<Professor> listarPorCurso(int codCurso);
}
