package io.github.avaliacaodocentes.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AdministradorDAOTest {
    
    Administrador adm;
    AdministradorDAO admDao;
    
    @Before
    public void setUpBefore(){
         admDao = new AdministradorDAO();
         adm = new Administrador ("adm@email.com", "Admin", "senha");
    }
    
    @Test
    public void testCadastrarAdminValido() {
        assertTrue(admDao.cadastrarAdmin(adm));
    }
    
    @Test
    public void testCadastrarAdminEmailInvalido() {
        Administrador admInv = new Administrador ("email", "Admin", "senha");
        assertFalse(admDao.cadastrarAdmin(admInv));
    }
    
    @Test
    public void testCadastrarAdminEmailNull() {
        Administrador admInv = new Administrador (null, "Admin", "senha");
        assertFalse(admDao.cadastrarAdmin(admInv));
    }
    
    @Test
    public void testBuscarValido(){
        System.out.println(adm);
        assertNotNull(admDao.buscar(adm.getEmail()));
    }
    
    @Test
    public void testBuscarInvalido(){
        assertNull(admDao.buscar("null"));
    }
    
    @Test
    public void testBuscarInvalidoNull(){
        assertNull(admDao.buscar(null));
    }
    
    @Test
    public void testLoginAdminValido() {
        try {
            assertNotNull(admDao.loginAdmin(adm.getEmail(), adm.getSenha()));
        } catch (Exception ex) {
        }
    }
    
    @Test
    public void testLoginAdminEmailInvalido() {
        try {
            admDao.loginAdmin(null, adm.getSenha());
            fail("Exception deveria ser lançada");
        } catch (Exception ex) {
        }
    }
    
    @Test
    public void testLoginAdminSenhaInvalido() {
        try {
            admDao.loginAdmin(adm.getEmail(), null);
            fail("Exception deveria ser lançada");
        } catch (Exception ex) {
        }
    }
    
}
