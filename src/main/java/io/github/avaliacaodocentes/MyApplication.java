package io.github.avaliacaodocentes;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

import io.github.avaliacaodocentes.infraSecurity.CORSFilter;


@ApplicationPath("rest")
public class MyApplication extends ResourceConfig {

    public MyApplication() {
        packages("io.github.avaliacaodocentes.controller");
        register(CORSFilter.class);
    }
}
