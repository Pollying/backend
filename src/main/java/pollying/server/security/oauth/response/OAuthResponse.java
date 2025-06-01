package pollying.server.security.oauth.response;

import java.util.Map;

public interface OAuthResponse {
    String getName();

    String getEmail();

    void setAttribute(Map<String, Object> attribute);

    String getProvider();

    String getProviderId();
}
