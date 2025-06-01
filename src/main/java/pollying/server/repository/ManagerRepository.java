package pollying.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pollying.server.entity.Manager;

import java.util.List;
import java.util.Optional;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Optional<Manager> findByEmail(String email);
}
