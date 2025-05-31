package pollying.server.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Poll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Poll() {}

    public Poll(String title, Manager manager) {
        this.title = title;
        this.manager = manager;
        this.isComplete = false;
        this.completedAt = null;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void addParticipant(Participant participant) {
        this.participants.add(participant);
    }

    public void addItem(Item item) {
        this.items.add(item);
    }
}
