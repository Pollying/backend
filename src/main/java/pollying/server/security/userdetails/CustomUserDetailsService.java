package pollying.server.security.userdetails;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import pollying.server.entity.Manager;
import pollying.server.repository.ManagerRepository;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final ManagerRepository managerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Manager manager = managerRepository.findByEmail(username).orElseThrow(()
                -> new UsernameNotFoundException("유저를 찾을 수 없습니다:" + username));

        log.info("유저 로드 = {}" , manager.getEmail());

        return new CustomUserDetails(manager);
    }
}
