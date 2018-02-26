package io.github.avaliacaodocentes.dao.interfaces;

import io.github.avaliacaodocentes.model.Curso;

public interface CursoDaoInterface {

    public boolean cadastrar(Curso curso);
    public boolean editar(Curso curso, int codigo);
    public boolean remover(int codigo);
}
