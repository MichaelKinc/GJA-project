package cz.welli.letmein.models;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "devices")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DeviceType deviceTyp;

    @Column(nullable = true, length = 255)
    private String description;

    @ManyToMany
    Set<Tag> tags;

}
