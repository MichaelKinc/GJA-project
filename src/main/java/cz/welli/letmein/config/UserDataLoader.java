package cz.welli.letmein.config;

import cz.welli.letmein.models.User;
import cz.welli.letmein.models.UserRole;
import cz.welli.letmein.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Method of this class is used for database seeding
 */
@Component
public class UserDataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String... args) {
        loadUserData();
    }

    /**
     * Creates 3 default users if is no user present in database
     *
     * +----+--------------+------------+-----------+---------+------------+
     * | id | email        | first_name | last_name | password| user_role  |
     * +----+----------------+------------+-----------+-------+------------+
     *|  1 | admin@admin.cz | Admin      | Admin     | 123456 | ROLE_ADMIN |
     *|  2 | kiosk@kiosk.cz | Kiosk      | Kiosk     | 123456 | ROLE_KIOSK |
     *|  3 | user@user.cz   | User       | User      | 123456 | ROLE_USER  |
     *+----+--------------------------------------------------+------------+
     */
    private void loadUserData() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (userRepository.count() == 0) {

            User admin = new User();
            admin.setEmail("admin@admin.cz");
            admin.setFirstName("Admin");
            admin.setLastName("Admin");
            admin.setPassword(passwordEncoder.encode("123456"));
            admin.setUserRole(UserRole.ROLE_ADMIN);
            userRepository.save(admin);

            User kiosk = new User();
            kiosk.setEmail("kiosk@kiosk.cz");
            kiosk.setFirstName("Kiosk");
            kiosk.setLastName("Kiosk");
            kiosk.setPassword(passwordEncoder.encode("123456"));
            kiosk.setUserRole(UserRole.ROLE_KIOSK);
            userRepository.save(kiosk);

            User user = new User();
            user.setEmail("user@user.cz");
            user.setFirstName("User");
            user.setLastName("User");
            user.setPassword(passwordEncoder.encode("123456"));
            user.setUserRole(UserRole.ROLE_USER);
            userRepository.save(user);
        }
    }
}