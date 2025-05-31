package pollying.server.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Poll {
    @Id @GeneratedValue
    @Column(name = "poll_id")
    private Long id;

    private String title;
    private boolean isComplete;
    private LocalDateTime completedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Manager manager;

    @OneToMany(mappedBy = "poll")
    private List<Participant> participants = new ArrayList<>();

    @OneToMany(mappedBy = "poll")
    private List<Item> items = new ArrayList<>();

    public void addParticipant(Participant participant) {
        this.participants.add(participant);
        participant.setPoll(this);
    }

    public void addItem(Item item) {
        this.items.add(item);
        item.setPoll(this);
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
