package pollying.server.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AuthUser {
    private String email;
    private Long id;
    private String name;
}
