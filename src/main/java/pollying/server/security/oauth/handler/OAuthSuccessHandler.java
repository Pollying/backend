package pollying.server.security.oauth.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import pollying.server.security.jwt.JwtService;
import pollying.server.security.jwt.dto.JwtsDto;
import pollying.server.security.userdetails.CustomOAuth2User;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtService jwtService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();

        String email = oAuth2User.getEmail();

        String token = jwtService.createForLogin(email);
        Cookie access = createCookie("Authorization", token);

        log.info("login success = {}", email);

        response.addCookie(access);
        response.sendRedirect("http://localhost:5500");
    }

    private Cookie createCookie(String key, String token) throws JsonProcessingException {
        Cookie cookie = new Cookie(key, token);

        cookie.setPath("/");
        cookie.setHttpOnly(true);

        log.info("cookie created\ntoken = {}", token);

        return cookie;
    }
}
