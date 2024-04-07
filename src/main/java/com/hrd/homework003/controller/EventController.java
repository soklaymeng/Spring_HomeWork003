package com.hrd.homework003.controller;

import com.hrd.homework003.model.Events;
import com.hrd.homework003.model.dto.ApiResponse;
import com.hrd.homework003.model.dto.request.EventRequest;
import com.hrd.homework003.service.EventService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
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
    public ResponseEntity<?> getAllEvents(@Positive @RequestParam(defaultValue = "1") Integer page,@Positive @RequestParam(defaultValue = "2") Integer size){
        List<Events> eventsList= eventService.getAllEvents(page,size);
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
    public ResponseEntity<ApiResponse<Events>> getAllEventById(@Positive @PathVariable Integer id){
        Events events;

           events= eventService.getAllEventById(id);
           ApiResponse<Events> response= new ApiResponse<>(
                   "Get events by Id successfully",
                   events,
                   HttpStatus.OK,
                   HttpStatus.OK.value(),
                   LocalDateTime.now()
           );
           return ResponseEntity.ok(response);


    }
    //Insert
    @PostMapping
    public ResponseEntity<?>InsertEvents(@Valid @RequestBody EventRequest eventRequest){
        Events eventsList= eventService.InsertEvents(eventRequest);
        return ResponseEntity.ok(new ApiResponse<>(
                "Insert successfully",
                eventsList,
                HttpStatus.CREATED,
                201,
                LocalDateTime.now()
        ));
    }
    //Update
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEventById(@Positive @PathVariable Integer id,
                                             @RequestBody EventRequest eventRequest){
        Events events= eventService.updateEventById(id,eventRequest);
        ApiResponse<Events> eventsApiResponse= new ApiResponse<>(
                "Successfully to update new event",
                events,
                HttpStatus.OK,
                200,
                LocalDateTime.now()
        );
        return ResponseEntity.ok(eventsApiResponse);

    }
    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEventById(@Positive @PathVariable Integer id)  {
            Events events = eventService.deleteEventById(id);
            ApiResponse<Events> eventsApiResponse = new ApiResponse<>(
                    "Delete successfully!!!",
                    events,
                    HttpStatus.OK,
                    200,
                    LocalDateTime.now()
            );
            return ResponseEntity.ok(eventsApiResponse);
    }


}
