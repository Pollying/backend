package pollying.server.security.jwt.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class JwtsDto {
    private String accessToken;
    private String refreshToken;
}
