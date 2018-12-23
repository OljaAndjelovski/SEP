package com.ftn.uns.payment_gateway.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;

import java.util.Date;
import java.util.Map;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.ftn.uns.payment_gateway.config.JWTConstants.PREFIX;
import static com.ftn.uns.payment_gateway.config.JWTConstants.SECRET;

public class JWTTokenProvider {

    public String createToken(SessionToken sessionToken){
        String subject = "{\n\t\"username\": \""+sessionToken.getUsername()
                + "\",\n\t\"sessionId\": \"" + sessionToken.getSessionId() + "\"\n}";
        String token = JWT.create()
                .withSubject(subject)
                .withExpiresAt(new Date(System.currentTimeMillis() + JWTConstants.EXPIRATION_DATE))
                .sign(HMAC512(JWTConstants.SECRET.getBytes()));
        return JWTConstants.PREFIX + "" + token;
    }

    public SessionToken retrieveDataFromToken(String jsonToken){
        JsonParser parser = JsonParserFactory.getJsonParser();
        Map<String, Object> tokenPayload = parser.parseMap(jsonToken);
        return new SessionToken(Long.parseLong(tokenPayload.get("sessionId").toString()), tokenPayload.get("username").toString());
    }

    public String decodeJWT(String token){
        return JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                .build()
                .verify(token.replace(PREFIX, ""))
                .getSubject();
    }
}
