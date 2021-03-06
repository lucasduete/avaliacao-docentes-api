package io.github.avaliacaodocentes.controller;

import io.github.avaliacaodocentes.dao.interfaces.CriterioDaoInterface;
import io.github.avaliacaodocentes.factory.Fabrica;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("criterio")
public class CriterioController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("listarCriterios/")
    public Response listarCriterios() {

        try {
            CriterioDaoInterface criterioDao = Fabrica.criarFabricaDaoPostgres()
                                                        .criarCriterioDao();

            return Response.ok(criterioDao.listar()).build();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
