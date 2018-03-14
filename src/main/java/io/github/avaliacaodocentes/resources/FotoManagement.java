package io.github.avaliacaodocentes.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

public class FotoManagement {

    private static final String PATHNAME = "./";

    public static String encodeFoto(File foto) throws IOException {

        //Recebe um Obj File da Foto e Transforma em Array de Bytes
        byte[] bytes = new byte[(int)foto.length()];
        new FileInputStream(foto).read(bytes);

        //Encoda o Arary de Bytem em Base64
        String fotoBase64 = new String(Base64.getEncoder().encodeToString(bytes));

        //Retorna a Foto em Base64
        return fotoBase64;
    }

    public static File decodeFoto(String fotoBase64, String matProfessor) throws IOException {

        //Cria objeto File de foto no endereço PATHNAME com o nome sendo uma
        //String contendo a matricula do professor cujo a foto
        //se refere
        File foto = new File(PATHNAME + matProfessor);

        //Decodifica a foto de Base64 para Array de Bytes
        byte[] fotoBytes = Base64.getDecoder().decode(fotoBase64);

        //Escreve o Array de Bytes em um Arquivo
        FileOutputStream fileOutputStream = new FileOutputStream(foto.getPath());
        fileOutputStream.write(fotoBytes);

        //Retorna a Foto
        return foto;
    }

    public static File verifyExistsFoto(String matProfessor) {

        //Cria objeto File de foto no endereço PATHNAME com o nome sendo
        //uma String contendo a matricula do Professor cujo a foto
        //se refere
        File foto = new File(PATHNAME + matProfessor);

        //Se ja existe essa foto no disco retorna ela para evitar o processamento de decodificação
        //da foto em Base64 para a foto em Bytes
        if (foto.exists())
            return foto;

        //Retorna a Null se a Foto nao Existe
        return null;
    }


}