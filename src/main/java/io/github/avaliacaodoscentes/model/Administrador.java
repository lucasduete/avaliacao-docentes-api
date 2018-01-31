package io.github.avaliacaodoscentes.model;

import java.util.Objects;

public class Administrador {

    private String email;
    private String nome;
    private String senha;

    public Administrador() {

    }

    public Administrador(String email, String nome, String senha) {
        this.email = email;
        this.nome = nome;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Administrador that = (Administrador) o;
        return Objects.equals(email, that.email) &&
                Objects.equals(nome, that.nome) &&
                Objects.equals(senha, that.senha);
    }

    @Override
    public int hashCode() {

        return Objects.hash(email, nome, senha);
    }

    @Override
    public String toString() {
        return "Administrador{" +
                "email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
