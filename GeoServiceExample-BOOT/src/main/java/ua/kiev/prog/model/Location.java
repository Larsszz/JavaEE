package ua.kiev.prog.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import ua.kiev.prog.dto.LocationDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class Location {
    @Id
    @GeneratedValue
    private Long id;

    private String ip;
    private String city;
    private String region;
    private String country;

    private Location(String ip, String city, String region, String country) {
        this.ip = ip;
        this.city = city;
        this.region = region;
        this.country = country;
    }

    public static Location of(String ip, String city, String region, String country) {
        return new Location(ip, city, region, country);
    }

    public LocationDTO toDTO() {
        return LocationDTO.of(ip, city, region, country);
    }

    public static Location fromDTO(LocationDTO dto) {
        return of(dto.getIp(), dto.getCity(), dto.getRegion(), dto.getCountry());
    }
}
