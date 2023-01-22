package cz.welli.letmein.models;

import jakarta.persistence.*;

//import javax.persistence.*;

@Entity
public class RFIDTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    User user;

    public Long getId() {
        return id;
    }
}
