package io.github.avaliacaodoscentes;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

import io.github.avaliacaodoscentes.infraSecurity.CORSFilter;


@ApplicationPath("rest")
public class MyApplication extends ResourceConfig {

    public MyApplication() {
        packages("io.github.avaliacaodoscentes.controller");
        register(CORSFilter.class);
    }
}
