package pollying.server.security.resolver;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import pollying.server.dto.InputDevice;
import pollying.server.security.resolver.annotation.DeviceId;

@Component
@Slf4j
public class DeviceIdResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasParameterAnnotation = parameter.hasParameterAnnotation(DeviceId.class);
        boolean assignableFrom = InputDevice.class.isAssignableFrom(parameter.getParameterType());

        return hasParameterAnnotation && assignableFrom;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

        String deviceId = getDeviceId(request.getCookies());

        InputDevice dto = InputDevice.builder()
                .id(deviceId)
                .build();

        log.info("resolve device dto = {}", dto);

        return dto;
    }

    private String getDeviceId(Cookie[] cookies) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("deviceId")) {
                return cookie.getValue();
            }
        }
        return null;
    }
}
