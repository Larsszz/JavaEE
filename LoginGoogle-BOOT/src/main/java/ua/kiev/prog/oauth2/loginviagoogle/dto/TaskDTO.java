package ua.kiev.prog.oauth2.loginviagoogle.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class TaskDTO {
    private Long id;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm", timezone = "UTC")
    private Date date;

    private String text;

    @JsonCreator
    public TaskDTO(@JsonProperty(required = true) Date date,
                   @JsonProperty(required = true) String text) {
        this.date = date;
        this.text = text;
    }

    private TaskDTO(Long id, Date date, String text) {
        this.id = id;
        this.date = date;
        this.text = text;
    }

    public static TaskDTO of(Date date, String text) {
        return new TaskDTO(null, date, text);
    }
    public static TaskDTO of(Long id, Date date, String text) {
        return new TaskDTO(id, date, text);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
