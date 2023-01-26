package cz.welli.letmein.repositories;

import cz.welli.letmein.models.Place;
import cz.welli.letmein.models.Reservation;
import cz.welli.letmein.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("SELECT r FROM Reservation r WHERE r.place=?1 and r.start = ?2")
    public List<Reservation> findByDateTime(Place place, LocalDateTime dateTime);

    @Query("SELECT r FROM Reservation r WHERE r.pin=?1")
    public List<Reservation> findByPin(String pin);

    @Query("SELECT r FROM Reservation r WHERE r.user=?1 ORDER BY r.start DESC")
    public List<Reservation> findByUser(User user);

    @Query("SELECT r FROM Reservation r WHERE r.user=?1 and r.start > ?2 ORDER BY r.start DESC")
    public List<Reservation> findUpcomming(User user, LocalDateTime date);
}