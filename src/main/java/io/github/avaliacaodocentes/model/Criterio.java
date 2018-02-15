package io.github.avaliacaodocentes.model;

import java.util.Objects;

public class Criterio {

    private int codigo;
    private String pontoAvaliativo;
    private String emailAdministrador;

    public Criterio() {

    }

    public Criterio(int codigo, String pontoAvaliativo, String emailAdministrador) {
        this.codigo = codigo;
        this.pontoAvaliativo = pontoAvaliativo;
        this.emailAdministrador = emailAdministrador;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getPontoAvaliativo() {
        return pontoAvaliativo;
    }

    public void setPontoAvaliativo(String pontoAvaliativo) {
        this.pontoAvaliativo = pontoAvaliativo;
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
        Criterio criterio = (Criterio) o;
        return getCodigo() == criterio.getCodigo() &&
                Objects.equals(getPontoAvaliativo(), criterio.getPontoAvaliativo()) &&
                Objects.equals(getEmailAdministrador(), criterio.getEmailAdministrador());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getCodigo(), getPontoAvaliativo(), getEmailAdministrador());
    }

    @Override
    public String toString() {
        return "Criterio{" +
                "codigo=" + codigo +
                ", pontoAvaliativo='" + pontoAvaliativo + '\'' +
                ", emailAdministrador='" + emailAdministrador + '\'' +
                '}';
    }
}
