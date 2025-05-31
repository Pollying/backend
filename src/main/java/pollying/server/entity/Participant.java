package pollying.server.entity;

import jakarta.persistence.*;

@Entity
public class Participant {

    @Id @GeneratedValue
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

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
