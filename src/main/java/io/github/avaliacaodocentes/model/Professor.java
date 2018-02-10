package io.github.avaliacaodocentes.model;

import java.util.Objects;

public class Professor {

    private String nome;
    private String senha;
    private String matricula;
    private float nota;

    public Professor() {

    }

    public Professor(String nome, String senha, String matricula, float nota) {
        this.nome = nome;
        this.senha = senha;
        this.matricula = matricula;
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
        return nota == professor.nota &&
                Objects.equals(nome, professor.nome) &&
                Objects.equals(senha, professor.senha) &&
                Objects.equals(matricula, professor.matricula);
    }

    @Override
    public int hashCode() {

        return Objects.hash(nome, senha, matricula, nota);
    }

    @Override
    public String toString() {
        return "Professor{" +
                "nome='" + nome + '\'' +
                ", senha='" + senha + '\'' +
                ", matricula='" + matricula + '\'' +
                ", nota=" + nota +
                '}';
    }

    public boolean isEmpty() {

        if (    (nome.isEmpty() || nome == null)
                || (senha.isEmpty() || senha == null)
                || (matricula.isEmpty() || matricula==null)
                )

            return true;
        else
            return false;
    }
}
