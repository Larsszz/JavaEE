package ua.kiev.prog.oauth2.loginviagoogle.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kiev.prog.oauth2.loginviagoogle.dto.AccountDTO;
import ua.kiev.prog.oauth2.loginviagoogle.dto.TaskDTO;
import ua.kiev.prog.oauth2.loginviagoogle.dto.TaskToNotifyDTO;
import ua.kiev.prog.oauth2.loginviagoogle.model.Account;
import ua.kiev.prog.oauth2.loginviagoogle.model.Task;
import ua.kiev.prog.oauth2.loginviagoogle.repos.AccountRepository;
import ua.kiev.prog.oauth2.loginviagoogle.repos.TaskRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class GeneralServiceImpl implements GeneralService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Transactional
    @Override
    public void addAccount(AccountDTO accountDTO, List<TaskDTO> tasks) {
        if (accountRepository.existsByEmail(accountDTO.getEmail()))
            return; // do nothing

        Account account = Account.fromDTO(accountDTO);

        tasks.forEach((x) -> {
            Task task = Task.fromDTO(x);
            account.addTask(task);
        });

        accountRepository.save(account);
    }

    @Transactional
    @Override
    public void addTask(String email, TaskDTO taskDTO) {
        Account account = accountRepository.findByEmail(email);
        Task task = Task.fromDTO(taskDTO);

        account.addTask(task);

        accountRepository.save(account);
    }

    @Transactional(readOnly = true)
    @Override
    public List<TaskDTO> getTasks(String email, Pageable pageable) {
        List<TaskDTO> result = new ArrayList<>();
        List<Task> tasks = taskRepository.findByAccountEmail(email, pageable);

        tasks.forEach((x) -> result.add(x.toDTO()));
        return result;
    }

    @Transactional(readOnly = true)
    @Override
    public List<TaskToNotifyDTO> getTasksToNotify(Date now) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(now);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date from = calendar.getTime();

        calendar.add(Calendar.MINUTE, 1);
        Date to = calendar.getTime();

        return taskRepository.findTasksToNotify(from, to);
    }

    @Transactional(readOnly = true)
    @Override
    public Long count(String email) {
        return taskRepository.countByAccountEmail(email);
    }

    @Transactional
    @Override
    public void delete(List<Long> idList) {
        idList.forEach((x) -> taskRepository.deleteById(x));
    }
}
