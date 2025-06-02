package pollying.server.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pollying.server.security.jwt.JwtService;
import pollying.server.security.jwt.dto.JwtsDto;
import pollying.server.security.userdetails.CustomUserDetails;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;

    @PostMapping("/api/login")
    public ResponseEntity<?> login(Authentication authentication) {
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();

        JwtsDto jwtsDto = jwtService.create(principal.getEmail());

        Map<String, Object> response = new HashMap<>();

        response.put("code", HttpStatus.CREATED.value());
        response.put("message", "토큰이 생성되었습니다.");
        response.put("jwts", jwtsDto);

        log.info("토큰 생성 완료");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
