package ua.kiev.prog.oauth2.loginviagoogle.services;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import ua.kiev.prog.oauth2.loginviagoogle.dto.AccountDTO;
import ua.kiev.prog.oauth2.loginviagoogle.dto.TaskDTO;
import ua.kiev.prog.oauth2.loginviagoogle.dto.TaskToNotifyDTO;

import java.util.Date;
import java.util.List;

public interface GeneralService {
    void addAccount(AccountDTO accountDTO, List<TaskDTO> tasks);
    void addTask(String email, TaskDTO taskDTO);
    List<TaskDTO> getTasks(String email, Pageable pageable);
    List<TaskToNotifyDTO> getTasksToNotify(Date now);
    Long count(String email);
    void delete(List<Long> idList);
}
