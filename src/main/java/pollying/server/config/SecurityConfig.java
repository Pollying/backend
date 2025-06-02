package pollying.server.config;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.file.ConfigurationSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import pollying.server.security.filter.JwtFilter;
import pollying.server.security.oauth.handler.OAuthSuccessHandler;
import pollying.server.security.oauth.service.CustomOAuthUserService;

import java.util.Arrays;
import java.util.List;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomOAuthUserService oAuthUserService;
    private final OAuthSuccessHandler oAuthSuccessHandler;
    private final JwtFilter jwtFilter;



    @Bean
    public SecurityFilterChain securityChain(HttpSecurity http) throws Exception {
        http
                .formLogin((auth) -> auth.disable());

        http
                .csrf((auth) -> auth.disable());

        http
                .cors((cors) -> cors
                        .configurationSource(apiConfigurationSource()));

        http
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http
                .httpBasic((auth) -> auth.disable());

        http
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/api/login").authenticated()
                        .anyRequest().permitAll());

        http
                .oauth2Login((auth) -> auth
                        .userInfoEndpoint((endpoint) -> endpoint
                                .userService(oAuthUserService))
                        .successHandler(oAuthSuccessHandler));

        return http.build();
    }

    private UrlBasedCorsConfigurationSource apiConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of("http://localhost:3000", "http://localhost:5500"));
        configuration.setAllowedMethods(List.of("GET", "POST", "HEAD", "OPTIONS", "PUT", "DELETE"));
        configuration.setAllowCredentials(true);

        configuration.setExposedHeaders(Arrays.asList("Authorization", "Set-Cookie"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
