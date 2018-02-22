package io.github.avaliacaodocentes.infraSecurity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.SecurityContext;
import javax.xml.bind.DatatypeConverter;
import java.util.Calendar;
import java.util.Date;

public class TokenManagement {

    private final static String SECRET_KEYTOKEN = "S3c3t-k1Y-t0%{T0K2n{4P1(GPS)}%";

    public TokenManagement() {

    }

    public String gerarToken(String login,Integer limiteDias ) {

        SignatureAlgorithm algoritimoAssinatura = SignatureAlgorithm.HS512;

        Date agora = new Date();

        Calendar expira = Calendar.getInstance();
        expira.add(Calendar.DAY_OF_MONTH, limiteDias);

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEYTOKEN);
        SecretKeySpec key = new SecretKeySpec(apiKeySecretBytes, algoritimoAssinatura.getJcaName());

        JwtBuilder construtor = Jwts.builder()
                .setIssuedAt(agora)
                .setIssuer(login)
                .signWith(algoritimoAssinatura, key)
                .setExpiration(expira.getTime());

        return construtor.compact();
    }

    public Claims validaToken(String token) {

        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEYTOKEN))
                    .parseClaimsJws(token).getBody();

            System.out.println("Caims : " + claims);
            return claims;
        } catch(Exception ex){
            throw ex;
        }
    }


    public static String getToken(SecurityContext securityContext) {

        return securityContext
                .getUserPrincipal()
                .getName();
    }

    public static String getToken(ContainerRequestContext requestContext) {
        return requestContext
                .getSecurityContext()
                .getUserPrincipal()
                .getName();
    }

}
