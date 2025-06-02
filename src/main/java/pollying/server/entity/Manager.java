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

    @Column(unique = true)
    private String email;

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
}
