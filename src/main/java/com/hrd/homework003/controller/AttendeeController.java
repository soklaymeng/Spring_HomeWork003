package com.hrd.homework003.controller;

import com.hrd.homework003.model.Attendees;
import com.hrd.homework003.model.dto.ApiResponse;
import com.hrd.homework003.model.dto.request.AttendeeRequest;
import com.hrd.homework003.service.AttendeeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/attendee")
public class AttendeeController {
    private final AttendeeService attendeeService;

    public AttendeeController(AttendeeService attendeeService) {
        this.attendeeService = attendeeService;
    }

    // Get all attendees
    @GetMapping
    public ResponseEntity<ApiResponse<List<Attendees>>> findAllAttendees(@Positive @RequestParam(defaultValue = "1") Integer page,@Positive @RequestParam(defaultValue = "2") Integer size) {
        List<Attendees> attendeesList = attendeeService.getAllAttendees(page,size);
        ApiResponse<List<Attendees>> response = new ApiResponse<>(
                "Get all attendees successfully",
                attendeesList,
                HttpStatus.OK,
                HttpStatus.OK.value(),
                LocalDateTime.now()
        );
        return ResponseEntity.ok(response);
    }

    // Get attendee by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Attendees>> findAttendeeById(@Positive @PathVariable Integer id) {
         Attendees   attendee = attendeeService.findAttendeesById(id);
            ApiResponse<Attendees> response = new ApiResponse<>(
                    "Get attendee successfully",
                    attendee,
                    HttpStatus.OK,
                    HttpStatus.OK.value(),
                    LocalDateTime.now()
            );
            return ResponseEntity.ok(response);


    }

    // Insert new attendee
    @PostMapping
    public ResponseEntity<ApiResponse<Attendees>> insertAttendee(@Valid
            @RequestBody AttendeeRequest attendeeRequest) {
        Attendees attendee = attendeeService.insertAttendee(attendeeRequest);
        ApiResponse<Attendees> response = new ApiResponse<>(
                "Successfully inserted new attendee",
                attendee,
                HttpStatus.CREATED,
                201,
                LocalDateTime.now()
        );
        return ResponseEntity.ok(response);
    }
    //update
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAttendeeById(@Positive @PathVariable Integer id,@Valid @RequestBody AttendeeRequest attendeeRequest)  {
        Attendees updatedAttendee = attendeeService.updateAttendeeById(id, attendeeRequest);
        ApiResponse<Attendees> attendeesApiResponse = new ApiResponse<>(
                "Venue updated successfully",
                updatedAttendee,
                HttpStatus.OK,
                200,
                LocalDateTime.now()
        );
        return ResponseEntity.ok(attendeesApiResponse);
    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAttendeeById(@Positive @PathVariable Integer id) {
        attendeeService.deleteAttendeeById(id);
        return ResponseEntity.ok(new ResponseEntity<>(
                "Successfully",
                HttpStatus.OK
        ));
    }
}
