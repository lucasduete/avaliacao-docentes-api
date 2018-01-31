package io.github.avaliacaodoscentes.model;

import java.util.Objects;

public class Aluno {

    private String nome;
    private String Senha;
    private String matricula;
    private String emailAdministrador;

    public Aluno() {

    }

    public Aluno(String nome, String senha, String matricula, String emailAdministrador) {
        this.nome = nome;
        Senha = senha;
        this.matricula = matricula;
        this.emailAdministrador = emailAdministrador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String senha) {
        Senha = senha;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aluno aluno = (Aluno) o;
        return Objects.equals(nome, aluno.nome) &&
                Objects.equals(Senha, aluno.Senha) &&
                Objects.equals(matricula, aluno.matricula) &&
                Objects.equals(emailAdministrador, aluno.emailAdministrador);
    }

    @Override
    public int hashCode() {

        return Objects.hash(nome, Senha, matricula, emailAdministrador);
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "nome='" + nome + '\'' +
                ", Senha='" + Senha + '\'' +
                ", matricula='" + matricula + '\'' +
                ", emailAdministrador='" + emailAdministrador + '\'' +
                '}';
    }
}
