package pollying.server.security.oauth.response;

import java.util.Map;

public enum SocialLogin {
    KAKAO("kakao");

    private String name;

    SocialLogin(String name) {
        this.name = name;
    }

    public static OAuthResponse getResponse(String providerId, Map<String, Object> attribute) throws InstantiationException, IllegalAccessException {
        OAuthResponse response = null;

        if (providerId.equals(SocialLogin.KAKAO.name)) {
            response = new KakaoOAuthResponse();
        }

        response.setAttribute(attribute);
        return response;
    }
}
