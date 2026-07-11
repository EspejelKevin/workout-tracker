package com.workouttracker.shared.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.workouttracker.domain.entities.auth.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.time.expiration}")
    private int timeExpiration;

    public String generateToken(User user) {
        Algorithm algorithm = Algorithm.HMAC256(this.secretKey);

        return JWT.create()
                .withIssuer(this.issuer)
                .withSubject(user.getId().toString())
                .withClaim("email", user.getEmail())
                .withClaim("role", user.getRole().getRole())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + timeExpiration))
                .sign(algorithm);
    }

    public DecodedJWT validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secretKey);

            return JWT.require(algorithm)
                    .withIssuer(this.issuer)
                    .build()
                    .verify(token);
        } catch (JWTVerificationException ex) {
            throw new JWTVerificationException("Expired or invalid token");
        }
    }
}
