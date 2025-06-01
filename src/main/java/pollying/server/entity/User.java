package pollying.server.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String device;

    @OneToMany(mappedBy = "user")
    private List<Participant> participants = new ArrayList<>();

    @OneToOne(mappedBy = "user")
    private Manager manager = null;

    @Builder
    public User(String device) {
        this.device = device;
    }

    public void addParticipant(Participant participant) {
        this.participants.add(participant);
    }

    public void connect(Manager manager) {
        this.manager = manager;
    }
}
