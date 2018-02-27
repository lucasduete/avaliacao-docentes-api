package io.github.avaliacaodocentes.controller;

import io.github.avaliacaodocentes.dao.interfaces.RankingDaoInterface;
import io.github.avaliacaodocentes.factory.Fabrica;
import io.github.avaliacaodocentes.infraSecurity.Security;
import io.github.avaliacaodocentes.model.Professor;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path("ranking")
public class RankingController {

    @Security
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("rankingGeral/")
    public Response getRanking() {

        try {
            RankingDaoInterface rankingDao = Fabrica.criarFabricaDaoPostgres()
                                                        .criarRankingDao();

            List<Professor> ranking = rankingDao.gerarRanking();

            return Response.ok(ranking).build();

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

    @Security
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("rankingSemestral/{semestre}")
    public Response getRankingSemestral(@PathParam("semestre") String semestre) {

        if (semestre == null || semestre.isEmpty() || semestre.length() != 6)
            return Response.status(Response.Status.BAD_REQUEST).build();

        try {
            RankingDaoInterface rankingDao = Fabrica.criarFabricaDaoPostgres()
                    .criarRankingDao();

            List<Professor> ranking = rankingDao.gerarRankingSemestral(semestre);

            return Response.ok(ranking).build();

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }
}
