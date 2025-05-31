package pollying.server.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Poll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "poll_id")
    private Long id;

    private String title;
    private boolean isComplete;
    private LocalDateTime completedAt;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Manager manager;

    @OneToMany(mappedBy = "poll")
    private List<Participant> participants = new ArrayList<>();

    @OneToMany(mappedBy = "poll")
    private List<Item> items = new ArrayList<>();

    public Poll(String title, Manager manager, LocalDateTime completedAt) {
        this.title = title;
        this.manager = manager;
        this.isComplete = false;
        this.completedAt = completedAt;
    }

    public void addParticipant(Participant participant) {
        this.participants.add(participant);
    }

    public void addItem(Item item) {
        this.items.add(item);
    }
}
