package ua.kiev.prog.oauth2.loginviagoogle.dto;

public class AccountDTO {
    private final String email;
    private final String name;
    private final String pictureUrl;

    private AccountDTO(String email, String name, String pictureUrl) {
        this.email = email;
        this.name = name;
        this.pictureUrl = pictureUrl;
    }

    public static AccountDTO of(String email, String name, String pictureUrl) {
        return new AccountDTO(email, name, pictureUrl);
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }
}
