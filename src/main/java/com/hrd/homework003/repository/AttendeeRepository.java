package com.hrd.homework003.repository;

import com.hrd.homework003.model.Attendees;
import com.hrd.homework003.model.Events;
import com.hrd.homework003.model.dto.request.AttendeeRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AttendeeRepository {
    @Select("""
    SELECT * FROM attendees
    """)
    @Results(id = "attendeeMapping", value = {
            @Result(property = "attendeeName", column = "attendee_name"),
            @Result(property = "attendeeId", column = "attendee_id"),
            @Result(property = "email", column = "email"),

    })
    List<Attendees> getAllAttendees();
    @Select("""
        SELECT * FROM attendees WHERE attendee_id = #{id};
        """)
    @ResultMap("attendeeMapping")
    Attendees findAttendeesById(Integer id);

    @Select("""
        INSERT INTO attendees (attendee_name, email)
        VALUES (#{attendeeName}, #{email}) RETURNING *
        """)
    @ResultMap("attendeeMapping")
    Attendees insertAttendee(AttendeeRequest attendeeRequest);

    // Add update method if needed
    @Select("""
    UPDATE attendees
    SET attendee_name = #{attendee.attendeeName}, email = #{attendee.email}
    WHERE attendee_id = #{id} Returning *
    """)
    @ResultMap("attendeeMapping")
    Attendees updateAttendeeById(@Param("id") Integer id, @Param("attendee") AttendeeRequest attendeeRequest);
    //Delete
    @Select("""
        DELETE FROM attendees
        WHERE attendee_id= #{id}
        """)
    @ResultMap("attendeeMapping")
    void deleteAttendeeById(Integer id);

    @Select("""
        SELECT * FROM attendees
        JOIN event_attendee ea on attendees.attendee_id = ea.attendee_id
        WHERE event_id= #{id}
        """)
    @ResultMap("attendeeMapping")
    List<Attendees> getEventByAttendeeId(Integer id);
}
