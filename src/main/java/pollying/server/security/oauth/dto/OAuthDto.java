package pollying.server.security.oauth.dto;

import lombok.Data;

@Data
public class OAuthDto {
    private String name;
    private String email;
    private String role;
}
