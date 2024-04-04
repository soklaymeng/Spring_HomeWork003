package com.hrd.homework003.service.serviceImp;

import com.hrd.homework003.model.Venues;
import com.hrd.homework003.model.dto.request.VenuesRequest;
import com.hrd.homework003.repository.VenuesRepository;
import com.hrd.homework003.service.VenueService;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenueServiceImp implements VenueService {
    private final VenuesRepository venuesRepository;

    public VenueServiceImp(VenuesRepository venuesRepository) {
        this.venuesRepository = venuesRepository;
    }


    @Override
    public List<Venues> getAllVenues(Integer page, Integer size) {
        return venuesRepository.getAllVenues(page,size);
    }
        //find by id
    @Override
    public Venues findVenueById(Integer id) throws NotFoundException {
        if (venuesRepository.findVenueById(id)==null){
            throw new NotFoundException("Venue with id " + id + " not found");
        }
        return venuesRepository.findVenueById(id);
    }

    @Override
    public Venues insertVenue(VenuesRequest venuesRequest) {
        return venuesRepository.insertVenue(venuesRequest);
    }

    @Override
    public void deleteVenueById(Integer id) throws NotFoundException {
        if (venuesRepository.findVenueById(id)==null){
            throw new NotFoundException("Venue with id " + id + " not found");
        }
        venuesRepository.deleteVenueById(id);
    }

    @Override
    public Venues updateVenueById(Integer id, VenuesRequest venuesRequest) throws NotFoundException {
       if (venuesRepository.findVenueById(id)==null){
           throw new NotFoundException("Venue with id " + id + " not found");
       }
        return venuesRepository.updateVenueById(id, venuesRequest);
    }
    //update








}
