package pollying.server.security.oauth.response;

import java.util.Map;

public enum SocialLogin {
    KAKAO("kakao", new KakaoOAuthResponse());

    private String name;
    private OAuthResponse response;

    SocialLogin(String name, OAuthResponse response) {
        this.name = name;
        this.response = response;
    }

    public static OAuthResponse getResponse(String providerId, Map<String, Object> attribute) throws InstantiationException, IllegalAccessException {
        for (SocialLogin value : SocialLogin.values()) {
            if (value.name.equals(providerId)) {
                value.response.setAttribute(attribute);
                return value.response;
            }
        }
        return null;
    }
}
