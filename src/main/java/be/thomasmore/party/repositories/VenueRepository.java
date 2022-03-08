package be.thomasmore.party.repositories;

import be.thomasmore.party.model.Venue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VenueRepository extends CrudRepository<Venue,Integer> {
    Iterable<Venue>findByOutdoor(boolean outdoor);
    Iterable<Venue>findByIndoor(boolean indoor);
//    @Query("SELECT v from Venue v where v.capacity >=:min and v.capacity>=:max")
//    Iterable<Venue> findByCapacityBetween (@Param("min") int min , @Param("max") int max);
//    @Query("SELECT v from Venue v where v.capacity >=:max")
//    Iterable<Venue> findBySmaller (@Param("max") int max);
//    @Query("SELECT v from Venue v where v.capacity>=:min")
//    Iterable<Venue> findByBigger (@Param("min") int min);

    List<Venue> findByCapacityIsGreaterThanEqual(int max);
    List<Venue> findByCapacityBetween(int min, int max);
    List<Venue> findAllBy();

    // aan een list kunt ge vragen .size en Iterable niet --> is per default

}
