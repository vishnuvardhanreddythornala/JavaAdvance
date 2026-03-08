package com.cap.BookStroreRest.Config;

import com.cap.BookStroreRest.Entity.Book;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

public class JwtUtil {
    private final String SECRET_KEY = "sdgfhgkjljjlhgdzfghvjbknlkjrdtyfugihjjuytdyfghifcgfdgcvbjuytdgh5";
    private final int JWT_EXPIRATION = 900000;
    private final int REFRESH_EXPIRATION = 604800000;

    private SecretKey getSigningKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);

        return Keys.hmacShaKeyFor(keyBytes);

    }
    public String generateToken(String email){
        return generateToken(email, JWT_EXPIRATION);
    }
    public String generateRefreshToken(String email){
        return generateToken(email, REFRESH_EXPIRATION);
    }

    public String generateToken(String email, long expiration){
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey(),  SignatureAlgorithm.HS256)
                .compact();
    }
    public String extractEmail(String token){
        return extractClaim(token, Claims::getSubject);
    }
    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims =  extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    public Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }
    public Boolean validateToken(String token, String email){
        final String tokenEmail =  extractEmail(token);
        return (tokenEmail.equals(email) && !isTokenExpired(token));
    }

}
