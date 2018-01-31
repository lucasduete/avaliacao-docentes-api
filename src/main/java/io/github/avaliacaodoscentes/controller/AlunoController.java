package io.github.avaliacaodoscentes.controller;

import io.github.avaliacaodoscentes.model.Aluno;
import io.github.avaliacaodoscentes.model.AlunoDAO;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("aluno")
public class AlunoController {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("cadastrarAluno/")
    public Response cadastrarAdmin(Aluno aluno) {

        AlunoDAO alunoDao = new AlunoDAO();

        if (alunoDao.cadastrarAluno(aluno))
            return Response.ok().build();
        else
            return Response.status(Response.Status.BAD_REQUEST).build();

    }
}
