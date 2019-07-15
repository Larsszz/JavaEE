package ua.kiev.prog.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kiev.prog.model.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
