package ua.kiev.prog.oauth2.loginviagoogle.model;

import ua.kiev.prog.oauth2.loginviagoogle.dto.AccountDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Account {
    @Id
    @GeneratedValue
    private Long id;

    private String email;
    private String name;
    private String pictureUrl;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private List<Task> tasks = new ArrayList<>();

    public Account() {}

    private Account(String email, String name, String pictureUrl) {
        this.email = email;
        this.name = name;
        this.pictureUrl = pictureUrl;
    }

    public static Account of(String email, String name, String pictureUrl) {
        return new Account(email, name, pictureUrl);
    }

    public void addTask(Task task) {
        task.setAccount(this);
        tasks.add(task);
    }

    public AccountDTO toDTO() {
        return AccountDTO.of(email, name, pictureUrl);
    }

    public static Account fromDTO(AccountDTO accountDTO) {
        return Account.of(accountDTO.getEmail(), accountDTO.getName(), accountDTO.getPictureUrl());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
