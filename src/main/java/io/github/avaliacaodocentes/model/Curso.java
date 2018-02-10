package io.github.avaliacaodocentes.model;

import java.util.Objects;

public class Curso {

    private String nome;
    private int codigo;
    private String emailAdministrador;

    public Curso() {

    }

    public Curso(String nome, int codigo, String emailAdministrador) {
        this.nome = nome;
        this.codigo = codigo;
        this.emailAdministrador = emailAdministrador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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
        Curso curso = (Curso) o;
        return codigo == curso.codigo &&
                Objects.equals(nome, curso.nome) &&
                Objects.equals(emailAdministrador, curso.emailAdministrador);
    }

    @Override
    public int hashCode() {

        return Objects.hash(nome, codigo, emailAdministrador);
    }

    @Override
    public String toString() {
        return "Curso{" +
                "nome='" + nome + '\'' +
                ", codigo=" + codigo +
                ", emailAdministrador='" + emailAdministrador + '\'' +
                '}';
    }

    public boolean isEmpty() {

        if (    (nome.isEmpty() || nome == null) ||
                (emailAdministrador.isEmpty() || emailAdministrador == null)
                )

            return true;
        else
            return false;

    }
}
