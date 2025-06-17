package com.example.hms.Security;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    @Value("${hospital.jwt.secret}")
    private String secretKey;

   // private String secretKey=" ";


//    public JwtService() throws NoSuchAlgorithmException {
//        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
//        SecretKey sk=keyGenerator.generateKey();
//       secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
//    }

    public String generateJwt(String userName,String userRole){
        return Jwts.builder()
                .claims()
                .add(Map.of("userRole",userRole))
                .subject(userName)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*60*60*30))
                .and()
                .signWith(getSignInKey(),Jwts.SIG.HS256)
                .compact();

    }

    private SecretKey getSignInKey() {
//        byte[] keyBytes= Decoders.BASE64.decode(secretKey);
//                return Keys.hmacShaKeyFor(keyBytes);
        return Keys.hmacShaKeyFor(Base64.getDecoder().decode(secretKey));
    }

    public Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String getUsername(Claims claims){
        return claims.getSubject();
    }

    public Date getIssuedAt(Claims claims){
        return claims.getIssuedAt();
    }
    public Date getExpiringDate(Claims claims){
        return claims.getExpiration();
    }

    public String getUserRole(Claims claims){
        return claims.get("userRole",String.class);
    }
}
