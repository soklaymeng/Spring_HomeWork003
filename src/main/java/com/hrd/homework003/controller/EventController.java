package com.hrd.homework003.controller;

import com.hrd.homework003.model.Events;
import com.hrd.homework003.model.dto.ApiResponse;
import com.hrd.homework003.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/event")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }
    @GetMapping
    public ResponseEntity<?> getAllEvents(){
        List<Events> eventsList= eventService.getAllEvents();
        return ResponseEntity.ok(new ApiResponse<>(
                "Get all events ",
                eventsList,
                HttpStatus.OK,
                200,
                LocalDateTime.now()
        ));
    }
}
