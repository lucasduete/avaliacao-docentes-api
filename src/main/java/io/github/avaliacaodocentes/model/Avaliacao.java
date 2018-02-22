package io.github.avaliacaodocentes.model;

import java.time.LocalDate;
import java.util.Objects;

public class Avaliacao {

    private String comentario;
    private LocalDate data;

    private Avaliacao() {

    }

    public Avaliacao(String comentario, LocalDate data) {
        this.comentario = comentario;
        this.data = data;
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

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Avaliacao criterio = (Avaliacao) o;
        return Objects.equals(getComentario(), criterio.getComentario()) &&
                Objects.equals(getData(), criterio.getData());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getComentario(), getData());
    }

    @Override
    public String toString() {

        return "Avaliacao{" +
                "comentario='" + comentario + '\'' +
                ", data=" + data +
                '}';
    }
}
