package cz.welli.letmein.models;

import javax.persistence.*;
import java.util.Set;

/**
 * Entity of Device
 */
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
