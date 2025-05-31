package pollying.server.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String device;

    @OneToMany(mappedBy = "user")
    private List<Participant> participants = new ArrayList<>();

    public User() {}

    public User(String device) {
        this.device = device;
    }

    public void addParticipant(Participant participant) {
        this.participants.add(participant);
    }
}
