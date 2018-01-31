package io.github.avaliacaodocentes.model;

import java.util.Objects;

public class Aluno {

    private String nome;
    private String Senha;
    private String matricula;
    private String emailAdministrador;
    private int codCurso;

    public Aluno() {

    }

    public Aluno(String nome, String senha, String matricula, String emailAdministrador, int codCurso) {
        this.nome = nome;
        Senha = senha;
        this.matricula = matricula;
        this.emailAdministrador = emailAdministrador;
        this.codCurso = codCurso;
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

    public int getCodCurso() {
        return codCurso;
    }

    public void setCodCurso(int codCurso) {
        this.codCurso = codCurso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aluno aluno = (Aluno) o;
        return codCurso == aluno.codCurso &&
                Objects.equals(nome, aluno.nome) &&
                Objects.equals(Senha, aluno.Senha) &&
                Objects.equals(matricula, aluno.matricula) &&
                Objects.equals(emailAdministrador, aluno.emailAdministrador);
    }

    @Override
    public int hashCode() {

        return Objects.hash(nome, Senha, matricula, emailAdministrador, codCurso);
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "nome='" + nome + '\'' +
                ", Senha='" + Senha + '\'' +
                ", matricula='" + matricula + '\'' +
                ", emailAdministrador='" + emailAdministrador + '\'' +
                ", codCurso=" + codCurso +
                '}';
    }
}
