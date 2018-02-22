package io.github.avaliacaodocentes.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Avaliacao {

    private String comentario;
    private LocalDate data;
    private String matProfessor;
    private String matAluno;
    private ArrayList<Pontuacao> pontuacoes;

    {
        data = LocalDate.now();
    }

    private Avaliacao() {

    }

    public Avaliacao(String comentario, String matProfessor, String matAluno,
                     ArrayList<Pontuacao> pontuacoes) {

        this.comentario = comentario;
        this.matProfessor = matProfessor;
        this.matAluno = matAluno;
        this.pontuacoes = pontuacoes;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getMatProfessor() {
        return matProfessor;
    }

    public void setMatProfessor(String matProfessor) {
        this.matProfessor = matProfessor;
    }

    public String getMatAluno() {
        return matAluno;
    }

    public void setMatAluno(String matAluno) {
        this.matAluno = matAluno;
    }

    public ArrayList<Pontuacao> getPontuacoes() {
        return pontuacoes;
    }

    public void setPontuacoes(ArrayList<Pontuacao> pontuacoes) {
        this.pontuacoes = pontuacoes;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Avaliacao avaliacao = (Avaliacao) o;
        return Objects.equals(getComentario(), avaliacao.getComentario()) &&
                Objects.equals(getData(), avaliacao.getData()) &&
                Objects.equals(getMatProfessor(), avaliacao.getMatProfessor()) &&
                Objects.equals(getMatAluno(), avaliacao.getMatAluno()) &&
                Objects.equals(getPontuacoes(), avaliacao.getPontuacoes());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getComentario(), getData(), getMatProfessor(), getMatAluno(), getPontuacoes());
    }

    @Override
    public String toString() {

        return "Avaliacao{" +
                "comentario='" + comentario + '\'' +
                ", data=" + data +
                ", matProfessor='" + matProfessor + '\'' +
                ", matAluno='" + matAluno + '\'' +
                ", pontuacoes=" + pontuacoes +
                '}';
    }
}
