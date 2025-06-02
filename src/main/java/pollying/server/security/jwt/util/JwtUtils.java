package pollying.server.security.jwt.util;

public interface JwtUtils {
    long accessExpire = 10 * 60 * 1000;
    long refreshExpire = 2 * 60 * 60 * 1000;
    long temp = 3 * 60 * 1000;
}
