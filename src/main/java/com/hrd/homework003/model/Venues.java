package com.hrd.homework003.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Venues {
    private Integer venueId;
    private String venueName;
    private String location;

}
