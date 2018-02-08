package io.github.avaliacaodocentes.infraSecurity;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.SecurityContext;

public class TokenManagement {

    public static String getToken(SecurityContext securityContext) {

        return securityContext
                .getUserPrincipal()
                .getName();
    }

    public static String getToken(ContainerRequestContext requestContext) {
        return requestContext
                .getSecurityContext()
                .getUserPrincipal()
                .getName();
    }

}
