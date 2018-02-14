package io.github.avaliacaodocentes.model;

import java.util.Objects;

public class Professor {

    private String nome;
    private String senha;
    private String matricula;
    private String emailAdministrador;
    private float nota;

    public Professor() {

    }

    public Professor(String nome, String senha, String matricula,
                     String emailAdministrador, float nota) {

        this.nome = nome;
        this.senha = senha;
        this.matricula = matricula;
        this.emailAdministrador = emailAdministrador;
        this.nota = nota;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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
                Objects.equals(getSenha(), professor.getSenha()) &&
                Objects.equals(getMatricula(), professor.getMatricula()) &&
                Objects.equals(getEmailAdministrador(), professor.getEmailAdministrador());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getNome(), getSenha(), getMatricula(), getEmailAdministrador(), getNota());
    }

    @Override
    public String toString() {

        return "Professor{" +
                "nome='" + nome + '\'' +
                ", senha='" + senha + '\'' +
                ", matricula='" + matricula + '\'' +
                ", emailAdministrador='" + emailAdministrador + '\'' +
                ", nota=" + nota +
                '}';
    }

    public boolean isEmpty() {

        if (    (nome == null || nome.isEmpty())
                || (senha == null || senha.isEmpty())
                || (matricula==null || matricula.isEmpty())
                || (emailAdministrador == null || emailAdministrador.isEmpty() )
                )

            return true;
        else
            return false;
    }
}
