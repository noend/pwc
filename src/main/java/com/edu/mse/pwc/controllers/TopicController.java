package com.edu.mse.pwc.controllers;

import com.edu.mse.pwc.dtos.TopicDto;
import com.edu.mse.pwc.persistence.entities.Role;
import com.edu.mse.pwc.services.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/topics")
@RolesAllowed(value = {"MODERATOR", "ADMIN", "USER"})
public class TopicController {

    private final TopicService topicService;

    @GetMapping("/{id}/")
    public TopicDto getTopic(@PathVariable Long id) {
        return topicService.getTopic(id);
    }

    @GetMapping
    public List<TopicDto> getAllTopic() {
        return topicService.getAllTopics();
    }

    @PostMapping("/createTopic")
    @ResponseBody
    public TopicDto createTopic(@RequestBody TopicDto topic) {
        return topicService.createTopic(topic);
    }

    @PostMapping("/updateTopic")
    @ResponseBody
    public TopicDto updateTopic(@RequestBody TopicDto topic) {
        return topicService.updateTopic(topic);
    }

//    @ExceptionHandler(value = {TopicNotFoundException.class, ReplyNotFoundException.class})
//    protected ResponseEntity<ErrorDto> handleException(Exception e) {
//        ErrorDto build = ErrorDto.builder().message(e.getMessage()).build();
//        ResponseEntity<ErrorDto> error = new ResponseEntity<>(build, HttpStatus.INTERNAL_SERVER_ERROR);
//        return error;
//    }

}
