package pollying.server.security.jwt.authentication;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import pollying.server.security.userdetails.CustomUserDetailsService;

@RequiredArgsConstructor
@Component
@Slf4j
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final CustomUserDetailsService service;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();

        UserDetails details = service.loadUserByUsername(email);

        JwtAuthentication jwtAuthentication = new JwtAuthentication(details, true);

        log.info("jwt authentication 발급");
        return jwtAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthentication.class.isAssignableFrom(authentication);
    }
}
