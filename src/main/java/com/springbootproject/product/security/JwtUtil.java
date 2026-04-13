package com.springbootproject.product.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

@Component
public class JwtUtil {

    private static String secretKey;

    // Constructor - Har Baar Nayi Secret Key Generate Karega
    JwtUtil(){
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[32];
        random.nextBytes(key);
        secretKey = Base64.getEncoder().encodeToString(key);
    }

    // Secret Key Ko Signing Key Mein Convert Karna
    private Key getSignedKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Token Generate Karna
    public String generateToken(String username, List<String> roles){
        return Jwts.builder()
                .setSubject(username)
                .claim("roles", roles)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*2))
                .signWith(getSignedKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // extract

    // Token Validate Karna
    public Boolean validToken(String token, String username){
        return (extractUsername(token).equals(username) && !isTokenExpired(token));
    }
    // Token Se Username Extract Karna
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    // Token Se Expiration Date Extract Karna
    public Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
    }

    // Token Expired Check Karna
    public Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    // Token Se Roles Extract Karna
    public List<String>  extractRole(String token){
        return extractClaim(token,claims -> claims.get("roles", List.class));
    }


    // Generic Claim Extractor
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSignedKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }

}
