package io.github.avaliacaodocentes.model;

import java.sql.Connection;
import org.junit.Test;
import static org.junit.Assert.*;

public class ConexaoTest {

    @Test
    public void testGetConnection() throws Exception {
        System.out.println("getConnection");
        Connection result = Conexao.getConnection();
        assertNotNull(result);
    }
    
}
