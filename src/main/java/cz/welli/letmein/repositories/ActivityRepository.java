package cz.welli.letmein.repositories;

import cz.welli.letmein.models.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    public Activity findByEmail(String email);
}