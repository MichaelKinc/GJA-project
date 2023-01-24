package cz.welli.letmein.models;

//import jakarta.persistence.*;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, length = 255)
    private String description;

    @ManyToOne
    User user;

    @ManyToMany
    Set<Device> devices;

    public Long getId() {
        return id;
    }
}
