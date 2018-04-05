package io.github.avaliacaodocentes.controller;

import io.github.avaliacaodocentes.dao.interfaces.AvaliacaoDaoInterface;
import io.github.avaliacaodocentes.dao.interfaces.ProfessorDaoInterface;
import io.github.avaliacaodocentes.factory.Fabrica;
import io.github.avaliacaodocentes.infraSecurity.Security;
import io.github.avaliacaodocentes.infraSecurity.model.NivelAcesso;
import io.github.avaliacaodocentes.model.Avaliacao;
import io.github.avaliacaodocentes.model.Professor;
import io.github.avaliacaodocentes.resources.FotoManagement;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

        if (foto == null || matProfessor == null || matProfessor.isEmpty())
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

    @GET
    @Produces("image/jpeg")
    @Path("foto/{matProfessor}/")
    public Response getFotoProfessor(@PathParam("matProfessor") String matProfessor) {

        if (matProfessor == null || matProfessor.isEmpty())
            return Response.status(Response.Status.BAD_REQUEST).build();

        File foto = FotoManagement.verifyExistsFoto(matProfessor);

        if (foto != null)
            return Response.ok(foto).build();

        try {
            ProfessorDaoInterface professorDao = Fabrica.criarFabricaDaoPostgres().criarProfessorDao();

            String fotoBase64 = professorDao.retornarFoto(matProfessor);

            if (fotoBase64 == null)
                return Response.status(Response.Status.NO_CONTENT).build();

            foto = FotoManagement.decodeFoto(fotoBase64, matProfessor);

            return Response.ok(foto).build();

        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GET
    @Security({NivelAcesso.NIVEL_1, NivelAcesso.NIVEL_2})
    @Produces(MediaType.APPLICATION_JSON)
    @Path("comentarios/{matProfessor}")
    public Response listarComentarios(@PathParam("matProfessor") String matProfessor) {

        if (matProfessor == null || matProfessor.isEmpty())
            return Response.status(Response.Status.BAD_REQUEST).build();

        try {
            AvaliacaoDaoInterface avaliacaoDao = Fabrica.criarFabricaDaoPostgres()
                    .criarAvaliacaoDao();

            List<Avaliacao> avaliacoes = avaliacaoDao.listarPorProfessor(matProfessor);
            List<String> comentarios = new ArrayList<>();

            avaliacoes.forEach(avaliacao -> {
                String comentario = avaliacao.getComentario();
                if (comentario != null && !comentario.isEmpty())
                    comentarios.add(comentario);

                System.out.println("Comentario" + comentario);
            });

            System.out.printf("\n\ncomentarios : " + comentarios);

            return Response.ok(comentarios).build();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

}
