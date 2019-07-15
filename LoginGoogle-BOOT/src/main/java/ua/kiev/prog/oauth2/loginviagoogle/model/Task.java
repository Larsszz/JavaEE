package ua.kiev.prog.oauth2.loginviagoogle.model;

import ua.kiev.prog.oauth2.loginviagoogle.dto.TaskDTO;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Task {
    @Id
    @GeneratedValue
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    private String text;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Task() {}

    private Task(Date date, String text) {
        this.date = date;
        this.text = text;
    }

    public static Task of(Date date, String text) {
        return new Task(date, text);
    }

    public TaskDTO toDTO() {
        return TaskDTO.of(id, date, text);
    }

    public static Task fromDTO(TaskDTO taskDTO) {
        return Task.of(taskDTO.getDate(), taskDTO.getText());
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
