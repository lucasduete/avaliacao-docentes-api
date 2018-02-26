package io.github.avaliacaodocentes.controller;

import io.github.avaliacaodocentes.dao.postgres.ProfessorDaoPostgres;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("professor")
public class ProfessorController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("listarProfessores/")
    public Response listarProfessores() {

        try {
            ProfessorDaoPostgres professorDao = new ProfessorDaoPostgres();

            return Response.ok(professorDao.listarTodos()).build();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }


    }
}
