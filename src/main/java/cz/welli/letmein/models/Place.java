package cz.welli.letmein.models;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Entity
@Table(name = "places")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "TIME")
    private LocalTime start;

    @Column(nullable = false, columnDefinition = "TIME")
    private LocalTime end;

    @Column(nullable = false, length = 10)
    private Integer capacity;

    @Column(nullable = false, length = 10)
    private Integer timeWindow;

    @OneToMany(mappedBy = "place")
    Set<Reservation> reservations;

    @ManyToMany
    Set<Season> seasons;

    @ManyToOne
    Activity activity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getTimeWindow() {
        return timeWindow;
    }

    public void setTimeWindow(Integer timeWindow) {
        this.timeWindow = timeWindow;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Set<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(Set<Season> seasons) {
        this.seasons = seasons;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Reservation> findByDateTime(LocalDateTime concreteLocalDateTime) {
        Predicate<Reservation> byDateTime = reservation -> reservation.getStart().isEqual(concreteLocalDateTime);
        return reservations.stream().filter(byDateTime).collect(Collectors.toList());
    }
}
