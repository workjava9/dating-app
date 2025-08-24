package com.geomax.datingapp.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {
  private final SecretKey key;
  public JwtService(@Value("${app.jwt.secret}") String secret){ this.key = Keys.hmacShaKeyFor(secret.getBytes()); }

  public String extractUsername(String token){ return extractClaim(token, Claims::getSubject); }
  public <T> T extractClaim(String token, Function<Claims, T> resolver){
    return resolver.apply(Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody());
  }
  public String generateToken(UserDetails user){
    return Jwts.builder().setSubject(user.getUsername())
      .setIssuedAt(new Date())
      .setExpiration(new Date(System.currentTimeMillis()+ 3600_000))
      .signWith(key, SignatureAlgorithm.HS256).compact();
  }
  public boolean isTokenValid(String token, UserDetails user){
    return user.getUsername().equals(extractUsername(token));
  }
}
