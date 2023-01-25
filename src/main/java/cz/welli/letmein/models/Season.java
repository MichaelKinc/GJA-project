package cz.welli.letmein.models;

import javax.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "seasons")
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate start;

    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate end;

    @ManyToMany(mappedBy = "seasons")
    Set<Place> places;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(String start) {
        LocalDate startDate = LocalDate.parse(start);
        this.start = startDate;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(String end) {
        LocalDate endDate = LocalDate.parse(end);
        this.end = endDate;
    }

    public Set<Place> getPlaces() {
        return places;
    }

    public void setPlaces(Set<Place> places) {
        this.places = places;
    }
}
