package cz.welli.letmein.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "activities")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type;

    @OneToMany(mappedBy = "activity")
    Set<Place> places;

    public Long getId() {
        return id;
    }

    public Set<Place> getPlaces() {
        return places;
    }

    public String getType() {
        return type;
    }
}