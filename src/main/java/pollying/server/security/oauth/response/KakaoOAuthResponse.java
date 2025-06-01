package pollying.server.security.oauth.response;

import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
public class KakaoOAuthResponse implements OAuthResponse {

    private Map<String, Object> attribute;
    private Map<String, Object> kakaoAccount;

    @Override
    public String getName() {
        return (String) ((Map<String, Object>) kakaoAccount.get("profile")).get("nickname");
    }

    @Override
    public String getEmail() {
        return (String) kakaoAccount.get("email");
    }

    @Override
    public void setAttribute(Map<String, Object> attribute) {
        kakaoAccount = (Map<String, Object>) attribute.get("kakao_account");
        this.attribute = attribute;
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getProviderId() {
        return (String) attribute.get("id");
    }
}
