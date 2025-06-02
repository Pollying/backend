package pollying.server.security.userdetails;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import pollying.server.security.oauth.dto.OAuthDto;

import java.util.Collection;
import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
public class CustomOAuth2User implements OAuth2User {

    private final OAuthDto dto;

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> dto.getRole());
    }

    @Override
    public String getName() {
        return dto.getName();
    }

    public String getEmail() {
        return dto.getEmail();
    }
}
