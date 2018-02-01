package io.github.avaliacaodocentes.model;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class AlunoDAOTest {
    
    Aluno aluno;
    
    @Before
    public void setUpBefore(){
         aluno = new Aluno("Joao", "1234", "200010101099", "adm@email.com", 1);
    }
    
    @Test
    public void testCadastrarAluno() {
        System.out.println("cadastrarAluno");
        AlunoDAO alunoDao = new AlunoDAO();
        assertTrue(alunoDao.cadastrarAluno(aluno));
    }
    
    @Test
    public void testLoginAluno() {
        System.out.println("loginAluno");
        AlunoDAO alunoDao = new AlunoDAO();
        assertTrue(alunoDao.loginAluno(aluno.getMatricula(), aluno.getSenha()));
    }
    
}
