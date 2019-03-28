package com.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

public class TokenUtil {

    private static final Logger logger = LoggerFactory.getLogger(TokenUtil.class);

    private static String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkngXDsTy7Vuo1CcDu0mUP+3lN2JarCYJa6lDJhbBwUstEE/HCTtZwsgoWFJFb2T3WAxCsXlxYKo9DiWJbBeMV7TRTzKbAz0Wp4M6ygAWo3NLlzeOa+4cMOVQ9kq0doHormzxzA/tHXTx9BYBtZBJryuXL3+qxWsb0CLtMa/W6Gs1+zKxo3FGvW0t1QnoZfoJV1VF8kvSEsDpsmV3kqerE0cVPkn/BOZ22x32F8Q3k3g0+g9npWIUZ3zVn/pemuG7vYTtPjBKAJCKeFF86gW2Fqb0EjrGCZx7BCO4gV3Gb6vng2mLEb3DkIcGBelmE0rnYAmlYAtJnSxWVI8gNUhwlwIDAQAB";

    private static String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCSeBcOxPLtW6jUJwO7SZQ/7eU3YlqsJglrqUMmFsHBSy0QT8cJO1nCyChYUkVvZPdYDEKxeXFgqj0OJYlsF4xXtNFPMpsDPRangzrKABajc0uXN45r7hww5VD2SrR2geiubPHMD+0ddPH0FgG1kEmvK5cvf6rFaxvQIu0xr9boazX7MrGjcUa9bS3VCehl+glXVUXyS9ISwOmyZXeSp6sTRxU+Sf8E5nbbHfYXxDeTeDT6D2elYhRnfNWf+l6a4bu9hO0+MEoAkIp4UXzqBbYWpvQSOsYJnHsEI7iBXcZvq+eDaYsRvcOQhwYF6WYTSudgCaVgC0mdLFZUjyA1SHCXAgMBAAECggEATH1chUEDIEOa0lghkcAmJJCnAJpjgb1HdCrrv0tpGfmZaghPiBmvkZIXx7AHRNo0dY3Jkoc6V2HwCqA9uO4/Q1pRaIuj+jf/ybE1lWIgcHF4i1rfDKxHmp1W/5gZ0D2dduG8EHZMvPJinLaVY+0bHdik1pBUbU63MqXauvT6RVtUgZZP1hoD6O23ZYwMiCtib9gkisniaoLBn1CIZ4MDcWbn6dxne1yJLDeoVpP4ZFU3sVlmnekh5KcUhklNkdMvLOSeuchP0dFXDgQtwUDfJNd5JWFOVCo1f4yl3pAYZTvQp/+v4+6LDff4z4ft5HPjPrktGT85GRQDdJM6pX6u4QKBgQDWfyYj5UbiJxpVD+M4SChY/WIY8mSkYIEH5Tj/KfKddCOUdx4kBacMl47Q/131c1y/ztasVwVPA1CObNQthVcIftyuqhdp06VvO2GmiMlaBMAG0vB4mZ7Y47Tr5vW4atpV/G7iNpCQ5GriTLzg9VFAWzRT0/tNfOjmeUtBqukz5wKBgQCuz0WbdyMwjq6T9d83gFKbrNhupNMOVmjuSO9oDNQOyvnQrDYZsghOfEAs4VCoEwkI01bkE6eIxKn93xlEfG/ztqqcZ0y40LCbYZgbdB4s2966lj2n+IZp22wLIjZGuLlRxRb6uBuD+aGsLt9NO1fN6OwtsVcE3ie2X2pDFIBH0QKBgBf8tiYzgL7Pwqkakr17jE+PXoYNrWWYiTV50+rtJP2ovEXhIFTE8Q5+cHE87aQCV6/3kJhLTwOsCjkzQHMqwzXnAgTibqXeDlUnHd+C0omnmFXrBwtlmpcEt/ndpUMPaAVPqpLPqVEayj0uebzqkkCRMoPhY+wheOY1UE4yr8unAoGBAKawBlPEOs3mgBmebgbiWeMP4iE/EfGt+8iby42QDfU9HxEX5U6DMOCGIjupbduWqxq7SzOYejw0K1RlFBb2AVqHaBBTgBkLBhxj50Lmao+j6LJK6OfuV11BWbkUGmU9Z5jNMqhIPhSOSNeCqnNejs9Nt0eqvqtpmryQJydnuRrBAoGART1cwZj3Z3yLAqbWaxoOkCCoT0EGPTKi760R2eNaKwHn+lP9kO8y2DsvmtkwmANUoBOEW4tyqRjFxAAK+U5K6pkM9PHKq/q8+HbIUbCY1gg3KINHProsbFv3VCX+dcjuwMxtEPXna9GSTyn2ycMsiKLnVAuMBVG1antVQKrfGJ8=";

    private  static PrivateKey privateKeyObject;

    private static PublicKey publicKeyObject;

    private static long jwtExpirationInSec = 3600;

    static {
        try {
            //由密钥字节数组获取密钥规范
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey));
            //实例化密钥工厂
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            //生成密钥
            privateKeyObject = keyFactory.generatePrivate(keySpec);

            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey));
            KeyFactory x509KeyFactory = KeyFactory.getInstance("RSA");
            //生成公钥
            publicKeyObject = x509KeyFactory.generatePublic(x509KeySpec);

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }

    }

    public  static String generateToken(String sno){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInSec * 10000);

        HashMap<String, Object> claims = new HashMap<>();
        claims.put("sno", sno);

        return Jwts.builder().setClaims(claims).setSubject(sno).setIssuedAt(new Date()).setExpiration(expiryDate)
                .signWith(privateKeyObject).compact();
    }


    public static String getUserIdFromRequest(String token){
        if (StringUtils.hasText(token)) {
            long startTime=System.currentTimeMillis();
            String result = getClaimsFromJWT(token).get("sno").toString();
            //获取结束时间
            long endTime=System.currentTimeMillis();
            logger.info("Token解析时间 :{} ms",(endTime-startTime));
            return result;
        }

        return null;
    }

    public static Map<String,Object> getClaimsFromJWT(String jwt){
        try {
            Claims claims = Jwts.parser().setSigningKey(publicKeyObject).parseClaimsJws(jwt).getBody();
            return claims;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //判断Token
    public static Boolean verify(String token){

        return false;
    }

    public static String getPublicKey() {
        return publicKey;
    }

    public static void main(String[] args) {
        // 生成Token
        TokenUtil jwt =new TokenUtil();
        System.out.println(jwt.generateToken("111"));
        System.out.println(jwt.getUserIdFromRequest("eyJhbGciOiJSUzI1NiJ9.eyJzbm8iOiIxMTEiLCJyb2xlcyI6W10sInN1YiI6IjExMSIsImlhdCI6MTU1MzcwNjY4OSwiZXhwIjoxNTUzNzQyNjg5fQ.Cf6av1vxFxXJtTo3PCBbsQ2y91Nxnfnub1LJsJr0-NVxSh88YtO-iOrj0_YB-Ff2pxiMs2ltiw_0sS7nFjznvvS8B-J6UgkSA3c9d54w3Xfe1i8EEZBawcV9nGAfzt9Im7114BQeqcXXtgzbiHG8-r1jpAdrA4wfRkNCLld0sfp9o1O_U2aP1-d9Wezep_hFvvI6hjGcqiMeidBRD3QRK5F4MUhc65uyqa7g_YGXtiPXYsAedv_M33TelJOn5C_WjGmVkY5mBt8IT1mE3iiLYf4z1EyqFP2ErsjU4auxhNJoLCZQH87OPIt177P82HH5SjrSH5NsZ4NgQdKu4uRSuw\n"));

        System.out.println(jwt.getClaimsFromJWT("eyJhbGciOiJSUzI1NiJ9.eyJzbm8iOiIxMTEiLCJyb2xlcyI6W10sInN1YiI6IjExMSIsImlhdCI6MTU1MzcwNjg4OSwiZXhwIjoxNTUzNzQyODg5fQ.WySt5FmR5vfG9-t6ivC0yShDf4tMgwg_1XD2nJpS-EXRyvPmjlVdmfqWFK1QGRiwEqbSvnsyjFBrczgXW1MHDteyxlhhzjdp9zXaYQLuCYR09hh68dmzggOgrF9qzGN1HBoJP6wCYdqEmQ54l-CGrdDM2FrT_PfRUJEcurwZzCsxFqkmsS-tbeWNE8TJVYt6iJFan5P-MeSQg_b2khXvduyE9OM-vOlksxvAJGaWE2vRpF6dXp7jfNIx5z_yoRH7AJKQ6QqyNobNjye6-BG1U2sn7wgwUcyVaQsa2o6xmtOFh7VWwgQMf-Z10r5l4sXM6yS0SOr-Xnnf3bDTSvzxvw"));

    }
}