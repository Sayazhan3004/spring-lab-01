
package kz.iitu.springlab;

import kz.iitu.springlab.security.AppUser;
import kz.iitu.springlab.security.AppUserRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class SecurityDatabaseTest {

    @Autowired
    private AppUserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Test
    void checkSecurityUsers() {

        System.out.println("===== SECURITY DATABASE CHECK =====");

        checkUser("user", "user123");
        checkUser("admin", "admin123");

        System.out.println("===== CHECK FINISHED =====");
    }

    private void checkUser(String username, String password) {

        AppUser user = repository.findByUsername(username)
                .orElse(null);

        if (user == null) {
            System.out.println(username + " EXISTS: false");
            return;
        }

        System.out.println(username + " EXISTS: true");

        System.out.println(
                username + " ENABLED: " + user.isEnabled()
        );

        System.out.println(
                username + " ROLES: " + user.getRoles()
        );

        System.out.println(
                username + " PASSWORD MATCHES: " +
                        encoder.matches(
                                password,
                                user.getPasswordHash()
                        )
        );
    }
}
