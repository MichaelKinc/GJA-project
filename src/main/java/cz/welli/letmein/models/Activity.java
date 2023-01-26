package cz.welli.letmein.models;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Entity of Activity
 */
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

    /**
     * Return id of activity
     * @return id of activity
     */
    public Long getId() {
        return id;
    }

    /**
     * Return places associated with activity
     * @return set of places
     */
    public Set<Place> getPlaces() {
        return places;
    }

    /**
     * Return type
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * Set type (name) of activity
     * @param type string of type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Set id of Activity
     * @param id id of activity
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Set places associated to activity
     * @param places set of places
     */
    public void setPlaces(Set<Place> places) {
        this.places = places;
    }
}