package io.github.avaliacaodocentes.controller;

import io.github.avaliacaodocentes.model.Administrador;
import io.github.avaliacaodocentes.dao.AdministradorDAO;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("admin")
public class AdministradorController {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("cadastrarAdmin/")
    public Response cadastrarAdmin(Administrador admin) {

        AdministradorDAO adminDao = new AdministradorDAO();

        if (adminDao.cadastrarAdmin(admin))
            return Response.ok().build();
        else
            return Response.status(Response.Status.BAD_REQUEST).build();

    }
}
