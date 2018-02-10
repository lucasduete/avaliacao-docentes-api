package io.github.avaliacaodocentes.controller;

import io.github.avaliacaodocentes.dao.AlunoDao;
import io.github.avaliacaodocentes.infraSecurity.Security;
import io.github.avaliacaodocentes.infraSecurity.TokenManagement;
import io.github.avaliacaodocentes.infraSecurity.model.NivelAcesso;
import io.github.avaliacaodocentes.model.Aluno;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("aluno")
public class AlunoController {

    @Security(NivelAcesso.NIVEL_2)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("editarConta/")
    public Response editarConta(Aluno aluno, @Context SecurityContext securityContext) {

        if(aluno.isEmpty())
            return Response.status(Response.Status.BAD_REQUEST).build();

        if (!aluno.getMatricula()
                .equals(TokenManagement
                        .getToken(securityContext)))
            return Response.status(Response.Status.UNAUTHORIZED).build();

        AlunoDao alunoDao = new AlunoDao();

        if(alunoDao.editar(aluno))
            return Response.ok().build();
        else
            return Response.status(Response.Status.BAD_REQUEST).build();

    }
}
