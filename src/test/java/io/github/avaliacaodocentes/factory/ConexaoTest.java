package io.github.avaliacaodocentes.factory;

import java.sql.Connection;
import static org.junit.Assert.*;
import org.junit.Test;

public class ConexaoTest {

    @Test
    public void testGetConnection() throws Exception {
        System.out.println("getConnection");
        Connection result = Conexao.getConnection();
        assertNotNull(result);
    }
    
}