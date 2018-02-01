package io.github.avaliacaodocentes.resources;

import org.junit.Test;
import static org.junit.Assert.*;

public class EncryptionTest {

    
    @Test
    public void testEncryptLenght() {
        String senha = "SenhaABC123";
        String result = Encryption.encrypt(senha);
        assertEquals(result.length(), 60);
    }
    
    @Test
    public void testEncryptLenghtBig() {
        String senha = "MWzLVDXFYprwQXauAKgJeXwLk4uC53SUVqyJr5JcTSKsFQQDeM3rpjSNZVej3ybbbKRD7HQcpdrusXXrrc5vFxxHNM5LENXEPAKQxXKcpQWgkQfMyBxj6trn";
        String result = Encryption.encrypt(senha);
        assertEquals(result.length(), 60);
    }

    @Test
    public void testEncryptLenghtEspecialChar() {
        String senha = "4zCu86+f=3w?n%=";
        String result = Encryption.encrypt(senha);
        assertEquals(result.length(), 60);
    }
    
    @Test
    public void testCheckPassword() {
        String senha = "SenhaABC123";
        String senha_hash = Encryption.encrypt(senha);
        assertTrue(Encryption.checkPassword(senha, senha_hash));
    }
    
    @Test
    public void testCheckPasswordBig() {
        String senha = "MWzLVDXFYprwQXauAKgJeXwLk4uC53SUVqyJr5JcTSKsFQQDeM3rpjSNZVej3ybbbKRD7HQcpdrusXXrrc5vFxxHNM5LENXEPAKQxXKcpQWgkQfMyBxj6trn";
        String senha_hash = Encryption.encrypt(senha);
        assertTrue(Encryption.checkPassword(senha, senha_hash));
    }
    
    @Test
    public void testCheckPasswordEspecialChar() {
        String senha = "4zCu86+f=3w?n%=";
        String senha_hash = Encryption.encrypt(senha);
        assertTrue(Encryption.checkPassword(senha, senha_hash));
    }
    
}
