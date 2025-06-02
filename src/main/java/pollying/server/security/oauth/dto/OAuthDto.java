package pollying.server.security.oauth.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OAuthDto {
    private String name;
    private String email;
    private String role;
}
