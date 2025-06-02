package pollying.server.entity;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class PollTest {

    @Autowired
    EntityManager em;

//    @Test
//    @DisplayName("poll 생성")
    void pollCreate() {
        // given
        Manager manager = null;

        Poll poll = Poll.builder()
                .title("first poll")
                .manager(manager)
                .completedAt(LocalDateTime.now())
                .build();

        Item item = Item.builder()
                .content("item1")
                .poll(poll)
                .build();

        poll.addItem(item);

        em.persist(poll);
        em.flush();
        em.clear();
        // when

        Poll findPoll = em.find(Poll.class, poll.getId());
        Item findItem = em.find(Item.class, item.getId());
        // then
        assertThat(findPoll.getItems().size()).isEqualTo(1);
        assertThat(findItem.getId()).isEqualTo(findPoll.getItems().get(0).getId());
        System.out.println(findPoll.getId());
    }
}
