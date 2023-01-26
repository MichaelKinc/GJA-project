package cz.welli.letmein.config;

import cz.welli.letmein.models.User;
import cz.welli.letmein.models.UserRole;
import cz.welli.letmein.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserDataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String... args) {
        loadUserData();
    }

    private void loadUserData() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (userRepository.count() == 0) {
            User admin = new User();
            admin.setEmail("admin@admin.cz");
            admin.setFirstName("Admin");
            admin.setLastName("Admin");
            admin.setPassword(passwordEncoder.encode("123456"));
            admin.setUserRole(UserRole.ROLE_ADMIN);

            User kiosk = new User();
            kiosk.setEmail("kiosk@kiosk.cz");
            kiosk.setFirstName("Kiosk");
            kiosk.setLastName("Kiosk");
            admin.setPassword(passwordEncoder.encode("123456"));
            kiosk.setUserRole(UserRole.ROLE_KIOSK);

            User user = new User();
            user.setEmail("user@user.cz");
            user.setFirstName("User");
            user.setLastName("User");
            admin.setPassword(passwordEncoder.encode("123456"));
            user.setUserRole(UserRole.ROLE_USER);

            userRepository.save(admin);
            userRepository.save(kiosk);
            userRepository.save(user);
        }
    }
}