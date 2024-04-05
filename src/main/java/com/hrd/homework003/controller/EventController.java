package com.hrd.homework003.controller;

import com.hrd.homework003.model.Events;
import com.hrd.homework003.model.dto.ApiResponse;
import com.hrd.homework003.model.dto.request.EventRequest;
import com.hrd.homework003.service.EventService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    //Get By ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Events>> getAllEventById(@PathVariable Integer id){
        Events events;
       try{
           events= eventService.getAllEventById(id);
           ApiResponse<Events> response= new ApiResponse<>(
                   "Get events by Id successully",
                   events,
                   HttpStatus.OK,
                   HttpStatus.OK.value(),
                   LocalDateTime.now()
           );
           return ResponseEntity.ok(response);
       } catch (NotFoundException e) {
           ApiResponse<Events> response= new ApiResponse<>(
                   "Get events by Id successfully",
                   null,
                   HttpStatus.NOT_FOUND,
                   HttpStatus.NOT_FOUND.value(),
                   LocalDateTime.now()
           );
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
       }

    }
    //Insert
    @PostMapping
    public ResponseEntity<?>InsertEvents(@RequestBody EventRequest eventRequest){
       // Events eventsList= eventService.InsertEvents(eventRequest);
        Events eventsList= eventService.InsertEvents(eventRequest);
        return ResponseEntity.ok(new ApiResponse<>(
                "Insert successfully",
                eventsList,
                HttpStatus.CREATED,
                201,
                LocalDateTime.now()
        ));
    }
}
