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

    /**
     * Get if of place
     * @return id of place
     */
    public Long getId() {
        return id;
    }

    /**
     * Set id of place
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get start of opening hours
     * @return start of opening hours
     */
    public LocalTime getStart() {
        return start;
    }

    /**
     * Set start of opening hours
     * @param start start of opening hours
     */
    public void setStart(LocalTime start) {
        this.start = start;
    }

    /**
     * Get end of opening hours
     * @return end of opening hours
     */
    public LocalTime getEnd() {
        return end;
    }

    /**
     * Set end of opening hours
     * @param end end of opening hours
     */
    public void setEnd(LocalTime end) {
        this.end = end;
    }

    /**
     * Get capacity of one time window
     * @return capacity
     */
    public Integer getCapacity() {
        return capacity;
    }

    /**
     * Set capacity of one time window
     * @param capacity capacity of time window
     */
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    /**
     * Get length of one time window
     * @return
     */
    public Integer getTimeWindow() {
        return timeWindow;
    }

    /**
     * Set length of time window
     * @param timeWindow length of time window
     */
    public void setTimeWindow(Integer timeWindow) {
        this.timeWindow = timeWindow;
    }

    /**
     * Get reservations associated with place
     * @return set of reservations
     */
    public Set<Reservation> getReservations() {
        return reservations;
    }

    /**
     * Set reservations associated with place
     * @param reservations  set of reservations
     */
    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    /**
     * Get seasons associated with place
     * @return set of places
     */
    public Set<Season> getSeasons() {
        return seasons;
    }

    /**
     * Set seasons associated with place
     * @param seasons  set of places
     */
    public void setSeasons(Set<Season> seasons) {
        this.seasons = seasons;
    }

    /**
     * Get activity associated with place
     * @return activity
     */
    public Activity getActivity() {
        return activity;
    }

    /**
     * Set activity
     * @param activity activity
     */
    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    /**
     * Get name of place
     * @return name of place
     */
    public String getName() {
        return name;
    }

    /**
     * Set name of place
     * @param name name of place
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Find reservation associated with place based on date time
     * @param concreteLocalDateTime date time
     * @return list of reservations
     */
    public List<Reservation> findByDateTime(LocalDateTime concreteLocalDateTime) {
        Predicate<Reservation> byDateTime = reservation -> reservation.getStart().isEqual(concreteLocalDateTime);
        return reservations.stream().filter(byDateTime).collect(Collectors.toList());
    }
}
