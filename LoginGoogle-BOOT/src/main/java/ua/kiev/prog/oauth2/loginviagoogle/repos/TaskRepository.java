package ua.kiev.prog.oauth2.loginviagoogle.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.kiev.prog.oauth2.loginviagoogle.dto.TaskToNotifyDTO;
import ua.kiev.prog.oauth2.loginviagoogle.model.Task;

import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByAccountEmail(String email, Pageable pageable);
    Long countByAccountEmail(String email);

    @Query("SELECT NEW ua.kiev.prog.oauth2.loginviagoogle.dto.TaskToNotifyDTO(a.email, t.date, t.text)" +
            "FROM Account a, Task t WHERE t.date >= :from AND t.date < :to")
    List<TaskToNotifyDTO> findTasksToNotify(@Param("from") Date from,
                                            @Param("to") Date to);
}
