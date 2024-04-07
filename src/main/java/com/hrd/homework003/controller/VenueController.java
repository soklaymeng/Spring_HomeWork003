package com.hrd.homework003.controller;

import com.hrd.homework003.model.dto.ApiResponse;
import com.hrd.homework003.model.Venues;
import com.hrd.homework003.model.dto.request.VenuesRequest;
import com.hrd.homework003.service.VenueService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/venue")
public class VenueController {
    private final VenueService venueService;
    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }
    @GetMapping
    public ResponseEntity<?> getAllVenues(@Positive @RequestParam(defaultValue = "1") Integer page,@Positive @RequestParam(defaultValue = "2")Integer size) {
        List<Venues> venuesList = venueService.getAllVenues(page, size);
       return ResponseEntity.ok(new ApiResponse<>(
                "Successfully",
               venuesList,
               HttpStatus.OK,
               200,
               LocalDateTime.now()
       ));
    }
    //Get Venues by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> findVenueById(@Valid @Positive @PathVariable Integer id ) {
        Venues venues= venueService.findVenueById(id);
        ApiResponse<Venues> venuesApiResponse= new ApiResponse<>(
                "successfully",
                venues,
                HttpStatus.OK,
                200,
                LocalDateTime.now()
        );
        return ResponseEntity.ok(venuesApiResponse);

    }
    //Insert
    @PostMapping
    public ResponseEntity<?> insertVenue(@Valid
            @RequestBody VenuesRequest venuesRequest){
        Venues venues1= venueService.insertVenue(venuesRequest);
        ApiResponse<Venues> venuesApiResponse= new ApiResponse<>(
                "Successfully insert new venue",
                venues1,
                HttpStatus.CREATED,
                201,
                LocalDateTime.now()
        );
        return ResponseEntity.ok(venuesApiResponse);
    }
    // Update
    @PutMapping("/{id}")
    public ResponseEntity<?> updateVenueById(@Positive @PathVariable Integer id, @RequestBody VenuesRequest venuesRequest)  {
        Venues updatedVenue = venueService.updateVenueById(id, venuesRequest);
        ApiResponse<Venues> response = new ApiResponse<>(
                "Venue updated successfully",
                updatedVenue,
                HttpStatus.OK,
                200,
                LocalDateTime.now()
        );
        return ResponseEntity.ok(response);
    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVenueById(@Positive @PathVariable Integer id)  {
        venueService.deleteVenueById(id);
        return ResponseEntity.ok(new ResponseEntity<>(
                "Successfully",
                HttpStatus.OK
        ));
    }

}
