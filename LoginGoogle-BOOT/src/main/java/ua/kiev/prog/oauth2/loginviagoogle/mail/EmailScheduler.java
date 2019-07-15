package ua.kiev.prog.oauth2.loginviagoogle.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ua.kiev.prog.oauth2.loginviagoogle.dto.TaskToNotifyDTO;
import ua.kiev.prog.oauth2.loginviagoogle.services.GeneralService;

import java.util.Date;
import java.util.List;

@Component
public class EmailScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private GeneralService generalService;

    @Scheduled(fixedDelay = 60000)
    public void sendNotifications() {
        List<TaskToNotifyDTO> tasks = generalService.getTasksToNotify(new Date());
        tasks.forEach((task) -> emailService.sendMessage(task));
    }
}
