package com.example.gateway.security;
import io.jsonwebtoken.Claims; import io.jsonwebtoken.Jwts; import org.springframework.beans.factory.annotation.Value; import org.springframework.stereotype.Component;
import java.util.Set; import java.util.stream.Collectors;
@Component public class JwtUtil {
    @Value("${jwt.secret:VerySecretKeyChangeMe}") private String jwtSecret;
    public boolean validateToken(String token){ try{ Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token); return true; }catch(Exception ex){ return false; } }
    public Set<String> getRoles(String token){ Claims b = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody(); Object roles = b.get("roles"); if(roles==null) return Set.of(); return ((java.util.List<?>)roles).stream().map(Object::toString).collect(Collectors.toSet()); }
}
