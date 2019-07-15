package ua.kiev.prog.json;

import lombok.Data;

@Data
public class Rate {
    private long timestamp;
    private String date;
    private SingleRate rates;
}
