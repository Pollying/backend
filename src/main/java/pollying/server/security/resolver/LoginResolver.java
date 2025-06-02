package pollying.server.security.resolver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import pollying.server.dto.AuthUser;
import pollying.server.security.resolver.annotation.Login;
import pollying.server.security.userdetails.CustomUserDetails;

@Component
@Slf4j
public class LoginResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {

        boolean haveAnnotation = parameter.hasParameterAnnotation(Login.class);
        boolean assignableFrom = AuthUser.class.isAssignableFrom(parameter.getParameterType());

        return haveAnnotation && assignableFrom;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        AuthUser userDto = AuthUser.builder()
                .id(userDetails.getId())
                .email(userDetails.getEmail())
                .name(userDetails.getUsername())
                .build();

        log.info("resolve user dto = {}", userDto);
        return userDto;
    }
}
