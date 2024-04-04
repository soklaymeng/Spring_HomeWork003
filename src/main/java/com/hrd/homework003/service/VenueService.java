package com.hrd.homework003.service;

import com.hrd.homework003.model.Venues;
import com.hrd.homework003.model.dto.request.VenuesRequest;
import org.apache.ibatis.javassist.NotFoundException;

import java.util.List;

public interface VenueService {
    List<Venues> getAllVenues(Integer page,Integer size);

    Venues findVenueById(Integer id) throws NotFoundException;

    Venues insertVenue(VenuesRequest venuesRequest);


    void deleteVenueById(Integer id) throws NotFoundException;

    Venues updateVenueById(Integer id, VenuesRequest venuesRequest) throws NotFoundException;
}
