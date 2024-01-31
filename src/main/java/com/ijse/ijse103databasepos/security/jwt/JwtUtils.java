package com.ijse.ijse103databasepos.security.jwt;
 
import java.security.Key;
import java.security.PublicKey;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {
    
    @Value("${app.secret}")
    private String jwtSecret;

    @Value("${app.jwtExpiration}")
    private int jwtExpiration;

    private Key key(){
      return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }
    public String generateJwtToken(Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
         return Jwts.builder()
            .subject(userDetails.getUsername())
            .issuedAt(new Date())
            .expiration(new Date(new Date().getTime() + jwtExpiration) )
            .signWith(key())
            .compact();
    }
    public boolean validateJwtToken(String authToken){
        try {
             Jwts.parser().verifyWith((SecretKey) key()).build().parse(authToken);
             return true;
        } catch (MalformedJwtException e) {
             System.err.println("Invalid Token");
        }catch(ExpiredJwtException e){
            System.err.println("Expired Token");
        }catch(UnsupportedJwtException e){
            System.err.println("Unsupported token format");
        }catch(IllegalArgumentException e){
            System.err.println("Blank Token");
        }
        return false;
    }
    public String getUserNameFromJwtToken(String authToken){
        return Jwts.parser().setSigningKey(key()).build().parseSignedClaims(authToken).getPayload().getSubject();

    }


}
