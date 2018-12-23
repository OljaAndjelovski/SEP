package com.ftn.uns.payment_gateway.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static com.ftn.uns.payment_gateway.config.JWTConstants.*;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(HEADER);

        if (header == null || !header.startsWith(PREFIX)) {
            chain.doFilter(req, res);
            return;
        }

        SessionToken token = getToken(req);

        SecurityContextHolder.getContext().setAuthentication(token);
        chain.doFilter(req, res);
    }

    private SessionToken getToken(HttpServletRequest request) {
        String token = request.getHeader(HEADER);
        if (token != null) {
            JWTTokenProvider tokenProvider = new JWTTokenProvider();
            String session = tokenProvider.decodeJWT(token);

            if (session != null) {
                return tokenProvider.retrieveDataFromToken(session);
            }
            return null;
        }
        return null;
    }
}
