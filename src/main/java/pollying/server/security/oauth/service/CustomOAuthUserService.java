package pollying.server.security.oauth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import pollying.server.entity.Manager;
import pollying.server.repository.ManagerRepository;
import pollying.server.security.oauth.details.CustomOAuth2User;
import pollying.server.security.oauth.dto.OAuthDto;
import pollying.server.security.oauth.response.OAuthResponse;
import pollying.server.security.oauth.response.SocialLogin;


@Service
@RequiredArgsConstructor
@Slf4j
public class CustomOAuthUserService extends DefaultOAuth2UserService {

    private final ManagerRepository repository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        try {
            OAuthResponse response = SocialLogin.getResponse(registrationId, oAuth2User.getAttributes());

            Manager findUser = repository.findByEmail(response.getEmail()).orElseGet(() -> {
                Manager manager = Manager.builder()
                        .name(response.getName())
                        .email(response.getEmail())
                        .build();
                return repository.save(manager);
            });

            OAuthDto dto = OAuthDto.builder()
                    .name(findUser.getName())
                    .email(findUser.getEmail())
                    .role("ROLE_MANAGER")
                    .build();

            log.info("success load user = {}", dto.toString());
            return new CustomOAuth2User(dto);

        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
