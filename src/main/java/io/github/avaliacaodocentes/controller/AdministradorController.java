package io.github.avaliacaodocentes.controller;

import io.github.avaliacaodocentes.dao.AlunoDao;
import io.github.avaliacaodocentes.dao.CursoDao;
import io.github.avaliacaodocentes.infraSecurity.Security;
import io.github.avaliacaodocentes.infraSecurity.model.NivelAcesso;
import io.github.avaliacaodocentes.model.Administrador;
import io.github.avaliacaodocentes.dao.AdministradorDao;
import io.github.avaliacaodocentes.model.Aluno;
import io.github.avaliacaodocentes.model.Curso;

import javax.print.attribute.standard.Media;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("admin")
public class AdministradorController {

    @Security(NivelAcesso.NIVEL_1)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("cadastrarAdmin/")
    public Response cadastrarAdmin(Administrador admin) {

        AdministradorDao adminDao = new AdministradorDao();

        if (adminDao.cadastrarAdmin(admin))
            return Response.ok().build();
        else
            return Response.status(Response.Status.BAD_REQUEST).build();

    }

    @Security(NivelAcesso.NIVEL_1)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("cadastrarAluno/")
    public Response cadastrarAdmin(Aluno aluno) {

        AlunoDao alunoDao = new AlunoDao();

        if (alunoDao.cadastrarAluno(aluno))
            return Response.ok().build();
        else
            return Response.status(Response.Status.BAD_REQUEST).build();

    }

    @Security(NivelAcesso.NIVEL_1)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("editarAdmin/{emailAdmin}")
    public Response editarAdmin(Administrador administrador,
                                @PathParam("emailAdmin") String emailAdmin,
                                @Context SecurityContext securityContext) {

        if (administrador.isEmpty())
            return Response.status(Response.Status.BAD_REQUEST).build();

        AdministradorDao administradorDao = new AdministradorDao();

        if (administradorDao.editar(administrador))
            return Response.ok().build();
        else
            return Response.status(Response.Status.BAD_REQUEST).build();

    }

    @Security(NivelAcesso.NIVEL_1)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("editarAluno/")
    public Response editarAluno(Aluno aluno) {

        if (aluno.isEmpty())
            return Response.status(Response.Status.BAD_REQUEST).build();

        AlunoDao alunoDao = new AlunoDao();

        if (alunoDao.editar(aluno))
            return Response.ok().build();
        else
            return Response.status(Response.Status.BAD_REQUEST).build();

    }

    @Security(NivelAcesso.NIVEL_1)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("cadastrarCurso/")
    public Response cadastrarCurso(Curso curso) {

        if (curso.isEmpty() || curso.getCodigo()<= 0)
            return Response.status(Response.Status.BAD_REQUEST).build();

        CursoDao cursoDao = new CursoDao();

        if (cursoDao.cadastrar(curso))
            return Response.ok().build();
        else
            return Response.status(Response.Status.BAD_REQUEST).build();
        
    }
}
