package com.hrd.homework003.repository;

import com.hrd.homework003.model.Venues;
import com.hrd.homework003.model.dto.request.VenuesRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface VenuesRepository {
    @Select("""
       SELECT * FROM venues;
        """)
    @Results (id = "venueMapping", value = {
            @Result(property = "venueName",column = "venue_name"),
            @Result(property = "venueId", column = "venue_id"),
            @Result(property = "location", column = "location")
    })
    List<Venues> getAllVenues(Integer page, Integer size);

    //get by ID
    @Select("""
         SELECT * FROM venues
         WHERE venue_id = #{id};
         """)
    @ResultMap("venueMapping")
    Venues findVenueById(Integer id);
    //Insert
    @Select("""
        INSERT INTO venues(venue_name,location)
        VALUES (#{venue.venueName},#{venue.location})
        RETURNING*
        """)
    @ResultMap("venueMapping")
    Venues insertVenue(@Param("venue")VenuesRequest venuesRequest);

    //Update
    @Select("""
    UPDATE venues
    SET venue_name = #{venue.venueName}, location = #{venue.location}
    WHERE venue_id = #{id} Returning *
    """)
    @ResultMap("venueMapping")
    Venues updateVenueById(@Param("id") Integer id, @Param("venue") VenuesRequest venuesRequest);

    //Delete
    @Select("""
        DELETE FROM venues
        WHERE venue_id= #{id}
        """)
    @ResultMap("venueMapping")
    void deleteVenueById(Integer id);


}
