package io.github.avaliacaodocentes.model;

import java.util.Objects;

public class Aluno {

    private String nome;
    private String senha;
    private String matricula;
    private String emailAdministrador;
    private int codCurso;

    public Aluno() {

    }

    public Aluno(String nome, String senha, String matricula, String emailAdministrador, int codCurso) {

        this.nome = nome;
        this.senha = senha;
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
        return getCodCurso() == aluno.getCodCurso() &&
                Objects.equals(getNome(), aluno.getNome()) &&
                Objects.equals(getSenha(), aluno.getSenha()) &&
                Objects.equals(getMatricula(), aluno.getMatricula()) &&
                Objects.equals(getEmailAdministrador(), aluno.getEmailAdministrador());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getNome(), getSenha(), getMatricula(), getEmailAdministrador(), getCodCurso());
    }

    @Override
    public String toString() {

        return "Aluno{" +
                "nome='" + nome + '\'' +
                ", senha='" + senha + '\'' +
                ", matricula='" + matricula + '\'' +
                ", emailAdministrador='" + emailAdministrador + '\'' +
                ", codCurso=" + codCurso +
                '}';
    }

    public boolean isEmpty() {

        if ((nome == null || nome.isEmpty())
                || (senha == null || senha.isEmpty())
                || (matricula == null || matricula.isEmpty())
                || (emailAdministrador == null || emailAdministrador.isEmpty()))

            return true;
        else
            return false;
    }
}
