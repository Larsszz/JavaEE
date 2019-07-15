package ua.kiev.prog.oauth2.loginviagoogle.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kiev.prog.oauth2.loginviagoogle.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean existsByEmail(String email);
    Account findByEmail(String email);
}
