package pollying.server.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pollying.server.dto.AuthUser;
import pollying.server.dto.InputDevice;
import pollying.server.security.jwt.JwtService;
import pollying.server.security.jwt.dto.JwtsDto;
import pollying.server.security.resolver.annotation.DeviceId;
import pollying.server.security.resolver.annotation.Login;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;

    @PostMapping("/api/login")
    public ResponseEntity<?> login(@Login AuthUser user) {
        log.info("user = {}", user);

        JwtsDto jwtsDto = jwtService.create(user.getEmail());

        Map<String, Object> response = new HashMap<>();

        response.put("code", HttpStatus.CREATED.value());
        response.put("message", "토큰이 생성되었습니다.");
        response.put("jwts", jwtsDto);

        log.info("토큰 생성 완료");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/api/device")
    public ResponseEntity<?> device(@DeviceId InputDevice device) {
        log.info("device id = {}", device.getId());

        return ResponseEntity.ok().body("ok");
    }
}
