package pollying.server.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "poll_id")
    private Poll poll;

    @OneToMany(mappedBy = "item")
    private List<Participant> participants = new ArrayList<>();

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public void addParticipant(Participant participant) {
        this.participants.add(participant);
        participant.setItem(this);
    }
}
