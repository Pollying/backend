package pollying.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pollying.server.entity.Manager;

import java.util.List;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
    List<Manager> findByEmail(String email);
}
