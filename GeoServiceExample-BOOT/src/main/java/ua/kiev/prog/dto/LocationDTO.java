package ua.kiev.prog.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LocationDTO {
    private String ip;
    private String city;
    private String region;
    private String country;

    private LocationDTO(String ip, String city, String region, String country) {
        this.ip = ip;
        this.city = city;
        this.region = region;
        this.country = country;
    }

    public static LocationDTO of(String ip, String city, String region, String country) {
        return new LocationDTO(ip, city, region, country);
    }
}
