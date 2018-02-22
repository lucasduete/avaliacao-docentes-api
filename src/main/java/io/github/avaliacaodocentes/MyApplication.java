package io.github.avaliacaodocentes;

import javax.ws.rs.ApplicationPath;

import io.github.avaliacaodocentes.infraSecurity.filter.FilterSecurityAuthentication;
import io.github.avaliacaodocentes.infraSecurity.filter.FilterSecurityAuthorization;
import org.glassfish.jersey.server.ResourceConfig;

import io.github.avaliacaodocentes.infraSecurity.filter.CORSFilter;

@ApplicationPath("rest")
public class MyApplication extends ResourceConfig {

    //Esta classe inicia a aplicaçao REST
    public MyApplication() {
        //Define-se os controladores REST da aplicaçao
        packages("io.github.avaliacaodocentes.controller");
        //Aplica o Filtro CORS na APIs
        register(CORSFilter.class);
        register(FilterSecurityAuthentication.class);
        register(FilterSecurityAuthorization.class);
    }
}
