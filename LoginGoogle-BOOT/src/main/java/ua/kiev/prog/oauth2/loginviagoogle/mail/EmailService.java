package ua.kiev.prog.oauth2.loginviagoogle.mail;

import ua.kiev.prog.oauth2.loginviagoogle.dto.TaskToNotifyDTO;

public interface EmailService {
    void sendMessage(TaskToNotifyDTO task);
}
