package ua.kiev.prog.oauth2.loginviagoogle.dto.results;

public class BadRequestResult extends ResultDTO {
    public BadRequestResult() {
        super("JSON deserialization failed");
    }
}
