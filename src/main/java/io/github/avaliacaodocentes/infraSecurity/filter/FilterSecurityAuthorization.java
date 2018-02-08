package io.github.avaliacaodocentes.infraSecurity.filter;

import io.github.avaliacaodocentes.controller.LoginController;
import io.github.avaliacaodocentes.infraSecurity.Security;
import io.github.avaliacaodocentes.infraSecurity.model.NivelAcesso;
import org.glassfish.jersey.internal.util.PropertyAlias;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Security
@Provider
@Priority(Priorities.AUTHORIZATION)
public class FilterSecurityAuthorization implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        Class<?> classe = resourceInfo.getResourceClass();
        List<NivelAcesso> nivelPermissaoClasse = extrairNivelPermissao(classe);

        Method metodo = resourceInfo.getResourceMethod();
        List<NivelAcesso> nivelPermisaoMetodo = extrairNivelPermissao(metodo);

        try {

            String login = requestContext.getSecurityContext().getUserPrincipal().getName();


            if (nivelPermisaoMetodo.isEmpty()) {
                checarPermissoes(nivelPermissaoClasse, login);
            } else {
                checarPermissoes(nivelPermisaoMetodo, login);
            }

        } catch (Exception e) {
            requestContext.abortWith(
                    Response.status(Response.Status.FORBIDDEN).build());
        }
    }

    private List<NivelAcesso> extrairNivelPermissao(AnnotatedElement annotatedElement) {

        if (annotatedElement == null) {
            return new ArrayList<NivelAcesso>();

        } else {
            Security secured = annotatedElement.getAnnotation(Security.class);

            if (secured == null) {
                return new ArrayList<NivelAcesso>();

            } else {
                NivelAcesso[] allowedRoles = secured.value();
                return Arrays.asList(allowedRoles);
            }
        }
    }

    private void checarPermissoes(List<NivelAcesso> nivelPermissaoPermitidos, String login)
            throws Exception {

        try {

            if(nivelPermissaoPermitidos.isEmpty())
                return;

            boolean temPermissao = false;

            NivelAcesso nivelPermissaoUsuario = new LoginController().buscarNivelPermissao(login);

            for (NivelAcesso nivelPermissao : nivelPermissaoPermitidos) {
                if(nivelPermissao.equals(nivelPermissaoUsuario)) {
                    temPermissao = true;
                    break;
                }
            }

            if(!temPermissao)
                throw new Exception("Cliente não possui o nível de permissão para esse método");

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
