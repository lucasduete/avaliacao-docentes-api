package io.github.avaliacaodocentes.dao.interfaces;

import io.github.avaliacaodocentes.model.Professor;

import java.util.List;

public interface ProfessorDaoInterface {

    public boolean cadastrar(Professor professor);
    public boolean editar(Professor professor);
    public boolean remover(String matricula);
    public List<Professor> listarTodos();
    public List<Professor> listarPorCurso(int codCurso);
    public boolean atualizarFoto(String foto, String matricula);
    public String retornarFoto(String matricula);
}
