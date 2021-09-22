package com.example.assignment.security.jwt;

import com.example.assignment.exception.ExpiredAccessTokenException;
import com.example.assignment.exception.ExpiredRefreshTokenException;
import com.example.assignment.exception.IncorrectTokenException;
import com.example.assignment.exception.InvalidTokenException;
import com.example.assignment.security.auth.CustomUserDetailsService;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.header}")
    private String header;

    @Value("${jwt.prefix}")
    private String prefix;

    @Value("${jwt.exp.access}")
    private Long accessTokenExpirationTime;

    @Value("${jwt.exp.refresh}")
    private Long refreshTokenExpirationTime;

    private final CustomUserDetailsService customUserDetailsService;

    protected String init() {
        return Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String createAccessToken(String username) {
        return Jwts.builder()
                .setHeaderParam("typ", "jwt")
                .setSubject(username)
                .claim("type", "access")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpirationTime))
                .signWith(SignatureAlgorithm.HS256, init())
                .compact();
    }

    public String createRefreshToken(String username) {
        return Jwts.builder()
                .setHeaderParam("typ", "jwt")
                .setSubject(username)
                .claim("type", "refresh")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenExpirationTime))
                .signWith(SignatureAlgorithm.HS256, init())
                .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(getUsername(token).getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String resolveToken(HttpServletRequest request) {
        String token = request.getHeader(header);

        if(StringUtils.hasText(token) && token.startsWith(prefix)) {
            return token.substring(7);
        }
        return null;
    }

    public Claims getUsername(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(init())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (MalformedJwtException | UnsupportedJwtException e) {
            throw new IncorrectTokenException();
        } catch (ExpiredJwtException e) {
            throw new ExpiredAccessTokenException();
        } catch (Exception e) {
            throw new InvalidTokenException();
        }
    }

    public boolean validateToken(String token) {
        return !getUsername(token)
                .getExpiration()
                .before(new Date());
    }

    public boolean isRefreshToken(String refreshToken) {
        try {
            return Jwts.parser()
                    .setSigningKey(init())
                    .parseClaimsJws(refreshToken)
                    .getBody()
                    .get("type").equals("refresh");
        } catch (MalformedJwtException | UnsupportedJwtException e) {
            throw new IncorrectTokenException();
        } catch (ExpiredJwtException e) {
            throw new ExpiredRefreshTokenException();
        } catch (Exception e) {
            throw new InvalidTokenException();
        }
    }

}
