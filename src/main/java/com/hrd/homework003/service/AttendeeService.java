package com.hrd.homework003.service;

import com.hrd.homework003.model.Attendees;
import com.hrd.homework003.model.dto.request.AttendeeRequest;
import org.apache.ibatis.javassist.NotFoundException;

import java.util.List;

public interface AttendeeService {

    List<Attendees> getAllAttendees(Integer page, Integer size);

    Attendees findAttendeesById(Integer id) ;

    Attendees insertAttendee(AttendeeRequest attendeeRequest);

    Attendees updateAttendeeById(Integer id, AttendeeRequest attendeeRequest);

    void deleteAttendeeById(Integer id);
}
