package com.hrd.homework003.repository;

import com.hrd.homework003.model.Events;
import com.hrd.homework003.model.Venues;
import com.hrd.homework003.model.dto.request.EventRequest;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface EventsRepository {
    @Select("""
        SELECT *FROM events;
        """)
    @Results(id = "eventMapping",value = {
            @Result(property = "eventId",column = "event_id"),
            @Result(property = "evenName", column = "event_name"),
            @Result(property = "eventDate", column = "event_date"),
            @Result(property = "venues",column = "venue_id",
            one = @One(select = "com.hrd.homework003.repository.VenuesRepository.findVenueById")
            ),
            @Result(property = "attendeesList",column = "event_id",
            many = @Many(select = "com.hrd.homework003.repository.AttendeeRepository.getEventByAttendeeId"))

    })
    List<Events> getAllEvent();
   //get by id
   @Select("""
    SELECT * FROM events
    WHERE event_id = #{id}
    """)
   @ResultMap("eventMapping")
    Events getAllEventById(Integer id);
   // Insert event with venueId and attendeeId(list)
    @Select("""
            INSERT INTO events (event_name, event_date, venue_id)
            VALUES (#{venues.eventName}, #{venues.eventDate}, #{venues.venueId})
            Returning event_id
    """)
    Integer InsertEvents(@Param("venues") EventRequest eventRequest);
    //Insert In to Event_Attendee table
    @Insert("""
        INSERT INTO event_attendee
        VALUES (#{eventId},#{attendeeId})
        """)
    void insertIntoEventAttendee(Integer eventId, Integer attendeeId);

    @Select("""
    SELECT * FROM events
    WHERE event_id = #{eventId}
""")
    @ResultMap("eventMapping")
    Events findEventById(Integer eventId);

}
