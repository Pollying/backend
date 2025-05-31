package pollying.server.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@DiscriminatorValue("M")
@NoArgsConstructor
public class Manager extends User{
    private String name;
    private String email;

    @OneToMany(mappedBy = "manager")
    private List<Poll> polls = new ArrayList<>();

    public Manager(String name, String email, String device) {
        super(device);
        this.name = name;
        this.email = email;
    }

    public void addPoll(Poll poll) {
        this.polls.add(poll);
    }
}
