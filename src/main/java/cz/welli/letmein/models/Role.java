package cz.welli.letmein.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    @Enumerated(EnumType.ORDINAL)
    private Type type;

    @OneToMany
    Set<User> users;

    public enum Type {
        Customer, Admin, Kiosk;
    }
}
