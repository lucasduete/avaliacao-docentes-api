package io.github.avaliacaodocentes.model;

import java.util.Objects;

public class Pontuacao {

    private int pontuacao;
    private int codCriterio;

    public Pontuacao() {

    }

    public Pontuacao(int pontuacao, int codCriterio) {
        this.pontuacao = pontuacao;
        this.codCriterio = codCriterio;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public int getCodCriterio() {
        return codCriterio;
    }

    public void setCodCriterio(int codCriterio) {
        this.codCriterio = codCriterio;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pontuacao pontuacao1 = (Pontuacao) o;
        return getPontuacao() == pontuacao1.getPontuacao() &&
                getCodCriterio() == pontuacao1.getCodCriterio();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getPontuacao(), getCodCriterio());
    }

    @Override
    public String toString() {

        return "Pontuacao{" +
                "pontuacao=" + pontuacao +
                ", codCriterio=" + codCriterio +
                '}';
    }
}
