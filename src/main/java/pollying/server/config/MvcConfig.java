package pollying.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .exposedHeaders("Authorization", "Set-Cookie")
                .allowedMethods("**")
                .allowedOrigins("http://localhost:3000", "http://localhost:5500");
    }
}
