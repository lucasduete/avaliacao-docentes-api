package io.github.avaliacaodocentes.controller;

import io.github.avaliacaodocentes.exceptions.CredenciaisInvalidasException;
import io.github.avaliacaodocentes.model.Administrador;
import io.github.avaliacaodocentes.model.AdministradorDAO;
import io.github.avaliacaodocentes.model.Aluno;
import io.github.avaliacaodocentes.model.AlunoDAO;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("login")
public class LoginController {


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("loginAdmin/{email}/{senha}")
    public Response loginAdmin(@PathParam("email") String email, @PathParam("senha") String senha) {

        try {
            AdministradorDAO adminDao = new AdministradorDAO();
            Administrador admin = adminDao.loginAdmin(email, senha);

            if (admin == null)
                return Response.status(Response.Status.NO_CONTENT).build();


            return Response.ok(admin).build();
        } catch (CredenciaisInvalidasException ciEx) {
            ciEx.printStackTrace();
            return Response.status(Response.Status.UNAUTHORIZED).build();
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("loginAluno/{matricula}/{senha}")
    public Response loginAluno(@PathParam("matricula") String matricula, @PathParam("senha") String senha) {

        try {
            AlunoDAO alunoDao = new AlunoDAO();
            Aluno aluno = alunoDao.loginAluno(matricula, senha);

            if (aluno == null)
                return Response.status(Response.Status.NO_CONTENT).build();


            return Response.ok(aluno).build();
        } catch (CredenciaisInvalidasException ciEx) {
            ciEx.printStackTrace();
            return Response.status(Response.Status.UNAUTHORIZED).build();
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
