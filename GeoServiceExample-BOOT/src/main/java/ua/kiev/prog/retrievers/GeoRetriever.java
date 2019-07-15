package ua.kiev.prog.retrievers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ua.kiev.prog.dto.LocationDTO;

@Component
public class GeoRetriever {
    private static final String URL = "http://ipinfo.io/";

    public LocationDTO getLocation(String ip) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<LocationDTO> response = restTemplate.getForEntity(URL + ip, LocationDTO.class);
        return response.getBody();
    }
}
