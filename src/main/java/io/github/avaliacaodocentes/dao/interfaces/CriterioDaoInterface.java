package io.github.avaliacaodocentes.dao.interfaces;

import io.github.avaliacaodocentes.model.Criterio;

import java.util.List;

public interface CriterioDaoInterface {

    public boolean adicionar(Criterio criterio);
    public boolean editar(Criterio criterio);
    public List<Criterio> listar();
    public boolean remover(int codigo);
}
