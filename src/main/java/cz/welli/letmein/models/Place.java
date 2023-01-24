package cz.welli.letmein.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "places")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

/*    @Column(nullable = false)
    private LocalDateTime from;

    @Column(nullable = false)
    private LocalDateTime to;*/

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
}
