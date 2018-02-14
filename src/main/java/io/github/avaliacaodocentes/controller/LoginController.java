package io.github.avaliacaodocentes.controller;

import io.github.avaliacaodocentes.exceptions.CredenciaisInvalidasException;
import io.github.avaliacaodocentes.infraSecurity.TokenManagement;
import io.github.avaliacaodocentes.infraSecurity.model.NivelAcesso;
import io.github.avaliacaodocentes.model.Administrador;
import io.github.avaliacaodocentes.dao.AdministradorDao;
import io.github.avaliacaodocentes.model.Aluno;
import io.github.avaliacaodocentes.dao.AlunoDao;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;
import java.sql.SQLException;

import java.util.Calendar;
import java.util.Date;

@Path("login")
public class LoginController {

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("loginAdmin/")
    public Response loginAdmin(@FormParam("email") String email,
                               @FormParam("senha") String senha) {

        try {
            AdministradorDao adminDao = new AdministradorDao();
            Administrador admin = adminDao.loginAdmin(email, senha);

            if (admin == null)
                return Response.status(Response.Status.NO_CONTENT).build();

            String token = new TokenManagement().gerarToken(email, 1);
            admin.setSenha(token);

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
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("loginAluno/")
    public Response loginAluno(@FormParam("matricula") String matricula,
                               @FormParam("senha") String senha) {

        try {
            AlunoDao alunoDao = new AlunoDao();
            Aluno aluno = alunoDao.loginAluno(matricula, senha);

            if (aluno == null)
                return Response.status(Response.Status.NO_CONTENT).build();

            String token = new TokenManagement().gerarToken(matricula, 1);
            aluno.setSenha(token);

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
