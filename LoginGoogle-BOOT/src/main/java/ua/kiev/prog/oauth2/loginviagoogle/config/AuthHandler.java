package ua.kiev.prog.oauth2.loginviagoogle.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import ua.kiev.prog.oauth2.loginviagoogle.dto.AccountDTO;
import ua.kiev.prog.oauth2.loginviagoogle.dto.TaskDTO;
import ua.kiev.prog.oauth2.loginviagoogle.services.GeneralService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class AuthHandler implements AuthenticationSuccessHandler {

    @Autowired
    private GeneralService generalService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException {
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken)authentication;
        OAuth2User user = token.getPrincipal();

        Map<String, Object> attributes = user.getAttributes();

        AccountDTO accountDTO = AccountDTO.of(
                (String) attributes.get("email"),
                (String) attributes.get("name"),
                (String) attributes.get("picture")
        );

        List<TaskDTO> tasks = Arrays.asList(
                TaskDTO.of(new Date(), "Test task 1"),
                TaskDTO.of(new Date(), "Test task 2"),
                TaskDTO.of(new Date(), "Test task 3"),
                TaskDTO.of(new Date(), "Test task 4"),
                TaskDTO.of(new Date(), "Test task 5"),
                TaskDTO.of(new Date(), "Test task 6")
        );

        generalService.addAccount(accountDTO, tasks);

        httpServletResponse.sendRedirect("/");
    }
}
