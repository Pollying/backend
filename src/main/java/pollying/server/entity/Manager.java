package pollying.server.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manager_id")
    private Long id;

    private String name;
    private String email;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "manager")
    private List<Poll> polls = new ArrayList<>();

    @Builder
    public Manager(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void addPoll(Poll poll) {
        this.polls.add(poll);
    }

    public void connect(User user) {
        this.user = user;
        user.connect(this);
    }

    public void disconnect() {
        this.user = null;
    }
}
