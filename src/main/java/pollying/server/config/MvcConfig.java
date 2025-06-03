package pollying.server.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pollying.server.security.resolver.DeviceIdResolver;
import pollying.server.security.resolver.LoginResolver;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class MvcConfig implements WebMvcConfigurer {

    private final LoginResolver loginResolver;
    private final DeviceIdResolver deviceIdResolver;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .exposedHeaders("Authorization", "Set-Cookie")
                .allowedMethods("**")
                .allowedHeaders("*")
                .allowedOrigins("http://localhost:3000", "http://localhost:5500");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(loginResolver);
        resolvers.add(deviceIdResolver);
    }
}
