package io.github.avaliacaodocentes.dao.interfaces;

import io.github.avaliacaodocentes.exceptions.CredenciaisInvalidasException;
import io.github.avaliacaodocentes.model.Administrador;

import java.sql.SQLException;

public interface AdministradorDaoInterface {

    public boolean cadastrar(Administrador admin);
    public Administrador buscar(String email);
    public boolean editar(Administrador administrador);
    public Administrador login(String email, String senha) throws CredenciaisInvalidasException, SQLException;
}
