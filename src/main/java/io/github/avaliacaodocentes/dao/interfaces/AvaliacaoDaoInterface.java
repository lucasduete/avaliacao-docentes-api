package io.github.avaliacaodocentes.dao.interfaces;

import io.github.avaliacaodocentes.model.Avaliacao;

import java.util.List;

public interface AvaliacaoDaoInterface {

    public boolean cadastrar(Avaliacao avaliacao);
    public boolean deletar(int codigo);
    public List<Avaliacao> listarPorProfessor(String matProfessor);
}
