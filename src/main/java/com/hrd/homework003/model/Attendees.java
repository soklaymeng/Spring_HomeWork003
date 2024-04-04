package com.hrd.homework003.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attendees {
    private Integer attendeeId;
    private String attendeeName;
    private String email;
}
