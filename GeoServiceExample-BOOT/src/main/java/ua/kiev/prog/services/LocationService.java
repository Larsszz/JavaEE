package ua.kiev.prog.services;

import org.springframework.data.domain.Pageable;
import ua.kiev.prog.dto.LocationDTO;

import java.util.List;

public interface LocationService {
    void save(LocationDTO dto);
    long count();
    List<LocationDTO> getLocations(Pageable pageable);
}
