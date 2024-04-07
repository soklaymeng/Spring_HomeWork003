package com.hrd.homework003.model.dto.request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {
    @NotNull(message = "Event's name should not be null...")
    @NotBlank(message = "Event's name should not be blank...")
    private String eventName;
    @NotNull(message = "Try again, eventDate could not be null...")
    private LocalDate eventDate;
    @NotNull(message = "Try again, venueId could not be null..")
    private Integer venueId;
    @NotNull(message = "Try again, attendeeId could not be null..")
    private List<Integer> attendeeId;
}
