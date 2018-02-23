package io.github.avaliacaodocentes.dao;

import io.github.avaliacaodocentes.model.Administrador;
import io.github.avaliacaodocentes.model.Professor;
import java.sql.SQLException;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProfessorDaoTest {
    
    private ProfessorDao profDao;
    private Professor professor;
    private final String MATRICULA_PROF_TEST = "0000000000000";
    
    private Administrador admTest;
    private AdministradorDao admDao;
    private final String EMAIL_ADM_TEST = "adm@test";
    
    
    public ProfessorDaoTest() throws SQLException, ClassNotFoundException {
        profDao = new ProfessorDao();
        
        admDao = new AdministradorDao();
        admTest = new Administrador(EMAIL_ADM_TEST, "test", "test");
        admDao.cadastrarAdmin(admTest);
    }

    @Test
    public void testCadastrar_CT01() {
        System.out.println("cadastrar - CT01");
        professor = new Professor("Test - CT01", "senha", MATRICULA_PROF_TEST+"01", EMAIL_ADM_TEST, 0);
        boolean result = profDao.cadastrar(professor);
        assertTrue(result);
    }

    //Esta cadastrando com ADM NULL
    @Test
    public void testCadastrar_CT02() {
        System.out.println("cadastrar - CT02");
        professor = new Professor("Test - CT02", "senha", MATRICULA_PROF_TEST+"02", null, 0);
        boolean result = profDao.cadastrar(professor);
        assertFalse(result);
    }
    
    @Test
    public void testCadastrar_CT03() {
        System.out.println("cadastrar - CT03");
        professor = new Professor("Test - CT03", "senha", null, EMAIL_ADM_TEST, 0);
        boolean result = profDao.cadastrar(professor);
        assertFalse(result);
    }
    
    @Test
    public void testEditar_CT04() throws SQLException, ClassNotFoundException {
        System.out.println("editar - CT04");
        professor = new Professor("Nome Test", "senha", MATRICULA_PROF_TEST+"04", EMAIL_ADM_TEST, 0);
        profDao.cadastrar(professor);
        
        profDao = new ProfessorDao();
        Professor novoProfessor = new Professor("Novo nome", "nova_senha", professor.getMatricula(), EMAIL_ADM_TEST, 5);
        boolean result = profDao.editar(novoProfessor);
        
        assertTrue(result);
    }
    
    //Nao esta editando porem retorna TRUE
    @Test
    public void testEditar_CT05() throws SQLException, ClassNotFoundException{
        System.out.println("editar - CT05");
        professor = new Professor("Test - CT05", "senha", MATRICULA_PROF_TEST+"05", EMAIL_ADM_TEST, 0);
        profDao.cadastrar(professor);
        
        profDao = new ProfessorDao();
        Professor novoProfessor = new Professor("Novo Test - CT05", "nova_senha", MATRICULA_PROF_TEST+"99", EMAIL_ADM_TEST, 5);
        boolean result = profDao.editar(novoProfessor);
        
        assertFalse(result);
    }
    
}
