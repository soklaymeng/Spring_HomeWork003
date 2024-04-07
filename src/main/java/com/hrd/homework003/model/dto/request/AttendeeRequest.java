package com.hrd.homework003.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendeeRequest {
    @NotNull(message = "Venue's name should not be null...")
    @NotBlank(message = "Venue's name should not be blank...")
    private String attendeeName;
    @Email(message = "Invalid email format, please try again...")
    private String email;
}
