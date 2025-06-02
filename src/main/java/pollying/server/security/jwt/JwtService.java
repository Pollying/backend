package pollying.server.security.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pollying.server.security.jwt.dto.JwtsDto;
import pollying.server.security.jwt.util.JwtProvider;
import pollying.server.security.jwt.util.JwtResolver;
import pollying.server.security.jwt.util.JwtUtils;

import static pollying.server.security.jwt.util.JwtUtils.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class JwtService {
    private final JwtProvider provider;
    private final JwtResolver resolver;

    public JwtsDto create(String email) {
        String access = provider.create(email, accessExpire);
        String refresh = provider.create(email, refreshExpire);

        JwtsDto dto = JwtsDto.builder()
                .accessToken(access)
                .refreshToken(refresh)
                .build();

        log.info("created tokens [{}] \nfrom [{}]", dto, email);

        return dto;
    }

    public String createForLogin(String email) {
        return provider.create(email, temp);
    }

    public String resolve(String token) {
        String email = resolver.resolve(token);

        log.info("resolved token [{}] to [{}]", token, email);

        return email;
    }
}
