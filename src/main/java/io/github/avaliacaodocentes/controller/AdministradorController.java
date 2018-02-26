package io.github.avaliacaodocentes.controller;

import io.github.avaliacaodocentes.dao.interfaces.*;
import io.github.avaliacaodocentes.factory.Fabrica;
import io.github.avaliacaodocentes.infraSecurity.Security;
import io.github.avaliacaodocentes.infraSecurity.TokenManagement;
import io.github.avaliacaodocentes.infraSecurity.model.NivelAcesso;
import io.github.avaliacaodocentes.model.*;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.sql.SQLException;

@Path("admin")
public class AdministradorController {

    @Security(NivelAcesso.NIVEL_1)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("cadastrarAluno/")
    public Response cadastrarAluno(Aluno aluno,
                                   @Context SecurityContext securityContext) {

        aluno.setEmailAdministrador(
                TokenManagement.getToken(securityContext)
        );

        if (aluno.isEmpty())
            return Response.status(Response.Status.BAD_REQUEST).build();

        try {
            AlunoDaoInterface alunoDao = Fabrica.criarFabricaDaoPostgres().criarAlunoDao();

            if (alunoDao.cadastrar(aluno))
                return Response.status(Response.Status.OK).build();
            else
                return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

    @Security(NivelAcesso.NIVEL_1)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("editarAluno/{matricula}")
    public Response editarAluno(Aluno aluno,
                                   @PathParam("matricula") String matricula,
                                   @Context SecurityContext securityContext) {

        if (aluno.isEmpty() || (!aluno.getMatricula().equals(matricula)))
            return Response.status(Response.Status.BAD_REQUEST).build();

        aluno.setEmailAdministrador(
                TokenManagement.getToken(securityContext)
        );

        try {
            AlunoDaoInterface alunoDao = Fabrica.criarFabricaDaoPostgres().criarAlunoDao();

            if (alunoDao.editar(aluno))
                return Response.status(Response.Status.OK).build();
            else
                return Response.status(Response.Status.BAD_REQUEST).build();

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

    @Security(NivelAcesso.NIVEL_1)
    @DELETE
    @Path("removeAluno/{matricula}")
    public Response removeAluno(@PathParam("matricula") String matricula) {

        try {
            AlunoDaoInterface alunoDao = Fabrica.criarFabricaDaoPostgres().criarAlunoDao();

            if (alunoDao.remover(matricula))
                return Response.status(Response.Status.OK).build();
            else
                return Response.status(Response.Status.BAD_REQUEST).build();

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

    @Security(NivelAcesso.NIVEL_1)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("cadastrarAdmin/")
    public Response cadastrarAdmin(Administrador admin) {

        try {
            AdministradorDaoInterface administradorDao = Fabrica
                                                            .criarFabricaDaoPostgres()
                                                                .criarAdministradorDao();

            if (administradorDao.cadastrar(admin))
                return Response.status(Response.Status.OK).build();
            else
                return Response.status(Response.Status.BAD_REQUEST).build();

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

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

        try {
            AdministradorDaoInterface administradorDao = Fabrica
                                                            .criarFabricaDaoPostgres()
                                                                .criarAdministradorDao();

            if (administradorDao.editar(administrador))
                return Response.status(Response.Status.OK).build();
            else
                return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

    @Security(NivelAcesso.NIVEL_1)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("removerAdmin/")
    public Response cadastrarAdmin(@FormParam("emailAdmin") String emailAdmin) {

        try {
            AdministradorDaoInterface administradorDao = Fabrica
                    .criarFabricaDaoPostgres()
                    .criarAdministradorDao();

            if (administradorDao.remover(emailAdmin))
                return Response.status(Response.Status.OK).build();
            else
                return Response.status(Response.Status.BAD_REQUEST).build();

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

    @Security(NivelAcesso.NIVEL_1)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("cadastrarCurso/")
    public Response cadastrarCurso(Curso curso,
                                   @Context SecurityContext securityContext) {

        curso.setEmailAdministrador(
                TokenManagement.getToken(securityContext)
        );

        if (curso.isEmpty() || curso.getCodigo()<= 0)
            return Response.status(Response.Status.BAD_REQUEST).build();

        try {
            CursoDaoInterface cursoDao = Fabrica.criarFabricaDaoPostgres().criarCursoDao();

            if (cursoDao.cadastrar(curso))
                return Response.status(Response.Status.OK).build();
            else
                return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Security(NivelAcesso.NIVEL_1)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("editarCurso/{codigo}")
    public Response editarCurso(Curso curso,
                                @PathParam("codigo") int codCurso,
                                @Context SecurityContext securityContext) {

        if (curso.isEmpty() || curso.getCodigo()<= 0)
            return Response.status(Response.Status.BAD_REQUEST).build();

        curso.setEmailAdministrador(
                TokenManagement.getToken(securityContext)
        );

        try {
            CursoDaoInterface cursoDao = Fabrica.criarFabricaDaoPostgres().criarCursoDao();

            if (cursoDao.editar(curso, codCurso))
                return Response.status(Response.Status.OK).build();
            else
                return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Security(NivelAcesso.NIVEL_1)
    @DELETE
    @Path("removerCurso/{codigo}")
    public Response removerCurso(@PathParam("codigo") int codCurso,
                                 @Context SecurityContext securityContext) {

        if (codCurso <= 0)
            return Response.status(Response.Status.BAD_REQUEST).build();

        try {
            CursoDaoInterface cursoDao = Fabrica.criarFabricaDaoPostgres().criarCursoDao();

            if (cursoDao.remover(codCurso))
                return Response.status(Response.Status.OK).build();
            else
                return Response.status(Response.Status.BAD_REQUEST).build();

        } catch (SQLException | ClassNotFoundException ex) {

            ex.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Security(NivelAcesso.NIVEL_1)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("cadastrarProfessor/")
    public Response cadastrarProfessor(Professor professor,
                                       @Context SecurityContext securityContext) {

        if (professor.isEmpty())
            return Response.status(Response.Status.BAD_REQUEST).build();

        professor.setEmailAdministrador(
                TokenManagement.getToken(securityContext)
        );

        professor.setNota(0);

        try {
            ProfessorDaoInterface professorDao = Fabrica.criarFabricaDaoPostgres()
                                                            .criarProfessorDao();

            if (professorDao.cadastrar(professor))
                return Response.status(Response.Status.OK).build();
            else
                return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

    @Security(NivelAcesso.NIVEL_1)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("editarProfessor/")
    public Response editarProfessor(Professor professor,
                                    @Context SecurityContext securityContext) {

        professor.setEmailAdministrador(
                TokenManagement.getToken(securityContext)
        );

        if (professor.isEmpty())
            return Response.status(Response.Status.BAD_REQUEST).build();

        try {
            ProfessorDaoInterface professorDao = Fabrica.criarFabricaDaoPostgres()
                                                            .criarProfessorDao();

            if (professorDao.editar(professor))
                return Response.status(Response.Status.OK).build();
            else
                return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

    @Security(NivelAcesso.NIVEL_1)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("cadastrarCriterio/")
    public Response cadastrarCriterio(Criterio criterio,
                                      @Context SecurityContext securityContext) {

        if (criterio.getPontoAvaliativo() == null || criterio.getPontoAvaliativo().isEmpty())
            return Response.status(Response.Status.BAD_REQUEST).build();

        criterio.setEmailAdministrador(
                TokenManagement.getToken(securityContext)
        );

        try {
            CriterioDaoInterface criterioDao = Fabrica.criarFabricaDaoPostgres()
                                                        .criarCriterioDao();

            if (criterioDao.adicionar(criterio))
                return Response.status(Response.Status.OK).build();
            else
                return Response.status(Response.Status.BAD_REQUEST).build();

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Security(NivelAcesso.NIVEL_1)
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("editarCriterio/")
    public Response editarCriterio(Criterio criterio) {

        if (criterio.getPontoAvaliativo() == null || criterio.getPontoAvaliativo().isEmpty())
            return Response.status(Response.Status.BAD_REQUEST).build();

        try {
            CriterioDaoInterface criterioDao = Fabrica.criarFabricaDaoPostgres()
                                                            .criarCriterioDao();

            if (criterioDao.editar(criterio))
                return Response.status(Response.Status.OK).build();
            else
                return Response.status(Response.Status.BAD_REQUEST).build();

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

}
