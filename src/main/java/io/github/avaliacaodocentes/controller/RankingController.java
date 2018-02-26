package io.github.avaliacaodocentes.controller;

import io.github.avaliacaodocentes.dao.interfaces.RankingDaoInterface;
import io.github.avaliacaodocentes.factory.Fabrica;
import io.github.avaliacaodocentes.infraSecurity.Security;
import io.github.avaliacaodocentes.model.Professor;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
    @Path("rankingGeral")
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
}
