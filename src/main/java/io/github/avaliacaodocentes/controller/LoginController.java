package io.github.avaliacaodocentes.controller;

import io.github.avaliacaodocentes.model.AdministradorDAO;
import io.github.avaliacaodocentes.model.AlunoDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("login")
public class LoginController {


    @POST
    @Path("loginAdmin/{email}/{senha}")
    public Response loginAdmin(@PathParam("email") String email, @PathParam("senha") String senha) {

        AdministradorDAO adminDao = new AdministradorDAO();

        if (adminDao.loginAdmin(email, senha))
            return Response.ok().build();
        else
            return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @POST
    @Path("loginAluno/{matricula}/{senha}")
    public Response loginAluno(@PathParam("matricula") String matricula, @PathParam("senha") String senha) {

        AlunoDAO alunoDao = new AlunoDAO();

        if (alunoDao.loginAluno(matricula, senha))
            return Response.ok().build();
        else
            return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
