package pollying.server.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Poll {
    @Id
    @GeneratedValue(generator = "uuids")
    @GenericGenerator(name= "uuid2", strategy = "uuid")
    @Column(name = "poll_id")
    private UUID id;

    private String title;
    private boolean isComplete = false;
    private LocalDateTime completedAt;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;

    @OneToMany(mappedBy = "poll")
    private List<Participant> participants = new ArrayList<>();

    @OneToMany(mappedBy = "poll", cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();

    @Builder
    public Poll(String title, Manager manager, LocalDateTime completedAt) {
        this.title = title;
        this.manager = manager;
        this.completedAt = completedAt;
    }

    public void addParticipant(Participant participant) {
        this.participants.add(participant);
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void earlyComplete() {
        complete();
        completedAt = LocalDateTime.now();
    }

    public void complete() {
        this.isComplete = true;
    }
}
