package pollying.server.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    private String content;
    private Integer count;

    @ManyToOne
    @JoinColumn(name = "poll_id")
    private Poll poll;

    @OneToMany(mappedBy = "item")
    private List<Participant> participants = new ArrayList<>();

    public Item(String content, Poll poll) {
        this.content = content;
        this.count = 0;
        this.poll = poll;
    }

    public void addParticipant(Participant participant) {
        this.participants.add(participant);
    }
}
