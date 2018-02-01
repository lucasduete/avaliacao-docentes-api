package io.github.avaliacaodocentes.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AdministradorDAOTest {
        
    Administrador adm;
    
    @Before
    public void setUpBefore(){
         adm = new Administrador("adm@email.com", "Admin", "1234");
    }
    
    @Test
    public void testCadastrarAdmin() {
        System.out.println("cadastrarAdmin");
        AdministradorDAO admDao = new AdministradorDAO();
        assertTrue(admDao.cadastrarAdmin(adm));
    }
    
    @Test
    public void testLoginAdmin() {
        System.out.println("loginAdmin");
        AdministradorDAO admDao = new AdministradorDAO();
        assertTrue(admDao.loginAdmin(adm.getEmail(), adm.getSenha()));
    }
    
}
