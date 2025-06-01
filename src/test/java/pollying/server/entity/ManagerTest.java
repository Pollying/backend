package pollying.server.entity;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback(value = false)
public class ManagerTest {

    @Autowired
    EntityManager em;

    @Test
    @DisplayName("manager test")
    void switchTest() {
        // given
        User user = User.builder()
                .device("name")
                .build();

        em.persist(user);

        Manager manager = Manager.builder()
                .name("name2")
                .email("email2")
                .build();

        manager.connect(user);
        em.persist(manager);

        em.flush();
        em.clear();
        // when

        User findUser = em.find(User.class, user.getId());

        if (findUser.getManager() != null) {
            findUser.getManager().disconnect();
        }


        Manager manager1 = Manager.builder()
                .email("email")
                .name("name")
                .build();

        em.persist(manager1);

        manager1.connect(findUser);

        // then
    }
}
