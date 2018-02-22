package io.github.avaliacaodocentes.model;

import java.util.Objects;

public class Pontuacao {

    private double pontuacao;
    private int codCriterio;

    public Pontuacao() {

    }

    public Pontuacao(double pontuacao, int codCriterio) {
        this.pontuacao = pontuacao;
        this.codCriterio = codCriterio;
    }

    public double getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(double pontuacao) {
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
        return Double.compare(pontuacao1.getPontuacao(), getPontuacao()) == 0 &&
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
