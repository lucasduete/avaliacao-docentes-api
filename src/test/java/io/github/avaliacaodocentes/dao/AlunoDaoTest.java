package io.github.avaliacaodocentes.dao;

import io.github.avaliacaodocentes.model.Aluno;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class AlunoDaoTest {
    
    AlunoDao alunoDao;
    
    @Before
    public void setUpBefore(){
        try {
            alunoDao = new AlunoDao();
        } catch (Exception ex) {
        }
    }
    
    @Test
    public void testCadastrarAlunoValido() {
        //Considerando que não há um usuário ja cadastrado com a mesma matricula
        //e exista o administrador adm@email.com e curso com código 1
        Aluno aluno = new Aluno("Joao", "1234", "200010109901", "adm@email.com", 1);
        assertTrue(alunoDao.cadastrar(aluno));
    }
    
    @Test
    public void testCadastrarAlunoInvalidoMat() {
        Aluno aluno = new Aluno("Maria", "1234", null, "adm@email.com", 1);
        assertFalse(alunoDao.cadastrar(aluno));
    }
    
    @Test
    public void testCadastrarAlunoInvalidoAdm() {
        //Considerando que não existe administrador 'invalido@email.com'
        Aluno aluno = new Aluno("Maria", "1234", "200010109902", "invalido@email.com", 1);
        assertFalse(alunoDao.cadastrar(aluno));
    }
    
    @Test
    public void testCadastrarAlunoInvalidoCurso() {
        //Considerando que não existe curso com código -1
        Aluno aluno = new Aluno("Carlos", "4321", "200010109903", "adm@email.com", -1);
        assertFalse(alunoDao.cadastrar(aluno));
    }
    
    @Test
    public void testBuscarValido(){
        assertNotNull(alunoDao.buscar("200010109901"));
    }
    
    @Test
    public void testBuscarInvalidoMat(){
        assertNull(alunoDao.buscar("null"));
    }
    
    @Test
    public void testBuscarInvalidoNull(){
        assertNull(alunoDao.buscar(null));
    }
        
    @Test
    public void testLoginAlunoValido() {
        try {
            assertNotNull(alunoDao.loginAluno("200010109901","1234"));
        } catch (Exception ex) {
        }
    }
    
    @Test
    public void testLoginAlunoInvalidoMat() {
        try {
            alunoDao.loginAluno("null","1234");
            fail("Exception deveria ser lançada");
        } catch (Exception ex) {
        }
    }
    
    public void testLoginAlunoInvalidoSenha() {
        try {
            alunoDao.loginAluno("200010109901","null");
            fail("Exception deveria ser lançada");
        } catch (Exception ex) {
        }
    }
    
}
