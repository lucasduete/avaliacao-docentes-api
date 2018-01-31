package io.github.avaliacaodocentes.resources;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Esta Classe Fornece Serviços para Criptografica usando a
 * API BCrypt.
 * jBCrypt: http://www.mindrot.org/projects/jBCrypt/
 * @version 1.0
 * @since 8.0, BCrypt 0.3m
 */

public class Encryption {

	// Define os "pulos" usados na geração do hash(Intervalo Válido 10-31)

	private static final int rounds = 12;

	/**
	 * Este Método é usado Para Converter um Texto em uma Hash usando o BCrypt.
	 * O Tamanho da Hash é sempre length=60.
	 * O valor de Rounds é Definido Acima como static final int, num intervalo de 10 a 31.
	 * O valor 12 é um padrão seguro.
	 * Gera um handles de 128 bits .
	 * Gera um handles 128-bit salt e Retorna um Hash.
	 * @param password_plaintext String que Contém a Senha em texto simples que Será Hasheada.
	 * @return String - Uma String com Length=60 Contendo uma Hash Criada pelo BCrypt(3).
	 */

	public static String encrypt(String password_plaintext) {
		String salt = BCrypt.gensalt(rounds);
		String hashed_password = BCrypt.hashpw(password_plaintext, salt);

		return(hashed_password);
	}

	/**
	 * Este Método pode ser Usado em Métodos como o Login onde Compara um Texto Simples (que será
	 * hasheada) com uma Hash já Armazenada Pelo Sistema.
	 * @param password_plaintext String que Contém a Senha em texto simples que Será Hasheada e Comparada.
	 * @param stored_hash Hash Salva no Banco de Dados.
	 * @return boolean - Retorna True se as Senhas forem Iguais, Retorna False se as Senhas foram Diferentes.
	 */
	public static boolean checkPassword(String password_plaintext, String stored_hash) {
		boolean password_verified = false;

		if(null == stored_hash || !stored_hash.startsWith("$2a$"))
			throw new java.lang.IllegalArgumentException("Hash Inválida.");

		password_verified = BCrypt.checkpw(password_plaintext, stored_hash);

		return password_verified;
	}

}
