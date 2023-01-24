package cz.welli.letmein.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

/*
    @Column(nullable = false)
    private LocalDateTime from;

    @Column(nullable = false)
    private LocalDateTime to;
*/

    @Column(nullable = false, length = 10)
    private Integer persons;

    @ManyToOne
    User user;

    @ManyToOne
    Place place;
}
