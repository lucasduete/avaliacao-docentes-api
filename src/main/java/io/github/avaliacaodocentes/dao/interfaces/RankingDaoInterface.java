package io.github.avaliacaodocentes.dao.interfaces;

import io.github.avaliacaodocentes.model.Professor;

import java.util.List;

public interface RankingDaoInterface {

    public List<Professor> gerarRanking();
}
