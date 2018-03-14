package io.github.avaliacaodocentes.controller;

import io.github.avaliacaodocentes.dao.interfaces.ProfessorDaoInterface;
import io.github.avaliacaodocentes.factory.Fabrica;
import io.github.avaliacaodocentes.infraSecurity.Security;
import io.github.avaliacaodocentes.infraSecurity.model.NivelAcesso;
import io.github.avaliacaodocentes.resources.FotoManagement;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

@Path("professor")
public class ProfessorController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("listarProfessores/")
    public Response listarProfessores() {

        try {
            ProfessorDaoInterface professorDao = Fabrica.criarFabricaDaoPostgres()
                                                            .criarProfessorDao();

            return Response.ok(professorDao.listarTodos()).build();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Security(NivelAcesso.NIVEL_1)
    @Consumes("image/jpeg")
    @Path("foto/{matProfessor}/")
    public Response setFotoProfessor(File foto,
                                     @PathParam("matProfessor") String matProfessor) {

        if (foto == null)
            return Response.status(Response.Status.BAD_REQUEST).build();

        try {
            ProfessorDaoInterface professorDao = Fabrica.criarFabricaDaoPostgres().criarProfessorDao();

            String fotoBase64 = FotoManagement.encodeFoto(foto);

            if (professorDao.atualizarFoto(fotoBase64, matProfessor))
                return Response.status(Response.Status.OK).build();
            else
                return Response.status(Response.Status.BAD_REQUEST).build();

        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
