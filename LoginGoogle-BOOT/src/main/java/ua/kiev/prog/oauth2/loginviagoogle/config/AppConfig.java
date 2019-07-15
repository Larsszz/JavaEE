package ua.kiev.prog.oauth2.loginviagoogle.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Value("${spring.mail.username}")
    private String fromAddress;

    @Bean
    @Scope("prototype")
    public SimpleMailMessage messageTemplate() {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setSubject("Task notification");
        message.setText("Please do not forget to complete the task:\n\n [%s] %s");
        message.setFrom(fromAddress);

        return message;
    }
}
