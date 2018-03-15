package io.github.avaliacaodocentes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

public class Professor {

    private String matricula;
    private String nome;
    private float nota;
    @JsonIgnore
    private String foto;
    @JsonIgnore
    private String emailAdministrador;


    public Professor() {

    }

    public Professor(String matricula, String nome, float nota, String foto, String emailAdministrador) {

        this.matricula = matricula;
        this.nome = nome;
        this.nota = nota;
        this.foto = foto;
        this.emailAdministrador = emailAdministrador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getEmailAdministrador() {
        return emailAdministrador;
    }

    public void setEmailAdministrador(String emailAdministrador) {
        this.emailAdministrador = emailAdministrador;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Professor professor = (Professor) o;
        return Float.compare(professor.getNota(), getNota()) == 0 &&
                Objects.equals(getNome(), professor.getNome()) &&
                Objects.equals(getMatricula(), professor.getMatricula()) &&
                Objects.equals(getEmailAdministrador(), professor.getEmailAdministrador());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getNome(), getFoto(), getMatricula(), getEmailAdministrador(), getNota());
    }

    @Override
    public String toString() {

        return "Professor{" +
                "nome='" + nome + '\'' +
                ", foto='" + foto + '\'' +
                ", matricula='" + matricula + '\'' +
                ", emailAdministrador='" + emailAdministrador + '\'' +
                ", nota=" + nota +
                '}';
    }

    public boolean isEmpty() {

        if (    (nome == null || nome.isEmpty())
                || (matricula==null || matricula.isEmpty()) )

            return true;
        else
            return false;
    }
}
