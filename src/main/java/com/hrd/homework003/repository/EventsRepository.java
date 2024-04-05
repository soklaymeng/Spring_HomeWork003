package com.hrd.homework003.repository;

import com.hrd.homework003.model.Events;
import com.hrd.homework003.model.Venues;
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

//    @Result(property = "attendeeName", column = "attendee_name"),
//    @Result(property = "attendeeId", column = "attendee_id"),
//    @Result(property = "email", column = "email"),
//    @Result(property = "eventsList",column = "attendee_id",
//    @Select("""
//    SELECT e.venue_id,e.event_name,e.event_date,e.venue_id
//    FROM event_attendee a JOIN events e ON a.event_id= e.event_id
//    WHERE attendee_id=#{id};
//    """)
//    @ResultMap("eventMapping")
//    List<Events> getEventByVenueId(Integer id);

}
