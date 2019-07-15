package ua.kiev.prog.services;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kiev.prog.dto.LocationDTO;
import ua.kiev.prog.model.Location;
import ua.kiev.prog.repo.LocationRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    @Transactional
    public void save(LocationDTO dto) {
        locationRepository.save(Location.fromDTO(dto));
    }

    @Override
    @Transactional(readOnly = true)
    public List<LocationDTO> getLocations(Pageable pageable) {
        List<Location> list = locationRepository.findAll(pageable).getContent();
        List<LocationDTO> res = new ArrayList<>();

        for (Location loc : list)
            res.add(loc.toDTO());

        return res;
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return locationRepository.count();
    }
}
