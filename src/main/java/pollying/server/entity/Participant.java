package pollying.server.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "participant_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "poll_id")
    private Poll poll;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Participant() {}

    public Participant(Poll poll, Item item, User user) {
        this.poll = poll;
        this.item = item;
        this.user = user;
    }
}
