package com.hrd.homework003.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Events {
    private Integer eventId;
    private String evenName;
    private LocalDate eventDate;
    private Venues venues;
    private List<Attendees> attendeesList;
}
