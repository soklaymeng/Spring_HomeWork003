package com.hrd.homework003.service;

import com.hrd.homework003.model.Attendees;
import com.hrd.homework003.model.dto.request.AttendeeRequest;
import org.apache.ibatis.javassist.NotFoundException;

import java.util.List;

public interface AttendeeService {

    List<Attendees> getAllAttendees();

    Attendees findAttendeeById(Integer id) throws NotFoundException;


    Attendees insertAttendee(AttendeeRequest attendeeRequest);

    Attendees updateAttendeeById(Integer id, AttendeeRequest attendeeRequest) throws NotFoundException;

    void deleteAttendeeById(Integer id);
}
