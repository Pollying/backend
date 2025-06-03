package pollying.server.security.filter;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import pollying.server.security.jwt.JwtService;
import pollying.server.security.jwt.authentication.JwtAuthentication;
import pollying.server.security.jwt.authentication.JwtAuthenticationProvider;
import pollying.server.security.userdetails.CustomUserDetails;

import java.io.IOException;

@RequiredArgsConstructor
@Component
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION = "Authorization";
    private final JwtAuthenticationProvider provider;
    private final JwtService jwtService;

    @PostConstruct
    public void init() {
        log.info("jwtFilter created");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        if (!requestURI.contains("/api/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

        log.info("start authentication");

        String token = getTokenFromRequest(request);

        if (token == null) {
            log.info("토큰 null");
            throw new IllegalArgumentException("인증 정보를 찾을 수 없습니다.");
        }

        String email = jwtService.resolve(token);

        JwtAuthentication jwtAuthentication = new JwtAuthentication(email);

        Authentication authenticate = provider.authenticate(jwtAuthentication);

        SecurityContextHolder.getContext().setAuthentication(authenticate);
        log.info("completed authentication");

        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String authorization = request.getHeader(AUTHORIZATION);
        String token = null;

        if (StringUtils.hasText(authorization)) {
            log.info("authorization header = {}", authorization);
            token = authorization.substring(7);
        } else {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(AUTHORIZATION)) {
                    return cookie.getValue();
                }
            }
        }

        return token;
    }
}
