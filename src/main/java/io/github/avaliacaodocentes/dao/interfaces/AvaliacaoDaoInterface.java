package io.github.avaliacaodocentes.dao.interfaces;

import io.github.avaliacaodocentes.model.Avaliacao;

public interface AvaliacaoDaoInterface {

    public boolean cadastrar(Avaliacao avaliacao);
    public boolean deletar(int codigo);
}
