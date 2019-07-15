package ua.kiev.prog.oauth2.loginviagoogle.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;
import ua.kiev.prog.oauth2.loginviagoogle.dto.*;
import ua.kiev.prog.oauth2.loginviagoogle.dto.results.BadRequestResult;
import ua.kiev.prog.oauth2.loginviagoogle.dto.results.ResultDTO;
import ua.kiev.prog.oauth2.loginviagoogle.dto.results.SuccessResult;
import ua.kiev.prog.oauth2.loginviagoogle.services.GeneralService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
public class MainController {

    private static final int PAGE_SIZE = 5;

    @Autowired
    private GeneralService generalService;

    @GetMapping("/account")
    public AccountDTO account(OAuth2AuthenticationToken auth) {
        Map<String, Object> attrs = auth.getPrincipal().getAttributes();

        String email = (String) attrs.get("email");
        String name = (String) attrs.get("name");
        String pictureUrl = (String) attrs.get("picture");

        return AccountDTO.of(email, name, pictureUrl);
    }

    @GetMapping("count")
    public PageCountDTO count(OAuth2AuthenticationToken auth) {
        String email = getEmail(auth);
        return PageCountDTO.of(generalService.count(email), PAGE_SIZE);
    }

    @GetMapping("tasks")
    public List<TaskDTO> tasks(OAuth2AuthenticationToken auth,
                               @RequestParam(required = false, defaultValue = "0") Integer page) {
        String email = getEmail(auth);

        return generalService.getTasks(email,
                PageRequest.of(
                        page,
                        PAGE_SIZE,
                        Sort.Direction.DESC,
                        "id"
                )
        );
    }

    @PostMapping("add")
    public ResponseEntity<ResultDTO> addTask(OAuth2AuthenticationToken auth,
                                             @RequestBody TaskDTO task) {
        String email = getEmail(auth);
        generalService.addTask(email, task);

        return new ResponseEntity<>(new SuccessResult(), HttpStatus.OK);
    }

    @PostMapping("delete")
    public ResponseEntity<ResultDTO> delete(@RequestParam(name = "toDelete[]", required = false) Long[] idList) {
        generalService.delete(Arrays.asList(idList));
        return new ResponseEntity<>(new SuccessResult(), HttpStatus.OK);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResultDTO> handleException() {
        return new ResponseEntity<>(new BadRequestResult(), HttpStatus.BAD_REQUEST);
    }

    private String getEmail(OAuth2AuthenticationToken auth) {
        return (String) auth.getPrincipal().getAttributes().get("email");
    }
}
