package be.thomasmore.party.repositories;

import be.thomasmore.party.model.Artist;
import be.thomasmore.party.model.Venue;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VenueRepository extends CrudRepository<Venue, Integer> {
    Iterable<Venue> findByOutdoor(boolean outdoor);

    Iterable<Venue> findByIndoor(boolean indoor);
//    @Query("SELECT v from Venue v where v.capacity >=:min and v.capacity>=:max")
//    Iterable<Venue> findByCapacityBetween (@Param("min") int min , @Param("max") int max);
//    @Query("SELECT v from Venue v where v.capacity >=:max")
//    Iterable<Venue> findBySmaller (@Param("max") int max);
//    @Query("SELECT v from Venue v where v.capacity>=:min")
//    Iterable<Venue> findByBigger (@Param("min") int min);

    //    List<Venue> findByCapacityIsGreaterThanEqual(int min);

    @Query("select v from Venue v where :min is null or :min >=v.capacity")
    List<Venue> findByCapacityIsGreaterThan(@Param("min") int min);

//    List<Venue> findByCapacityBetween(int min, int max);
    @Query("select v from Venue v where :max is null or :max <= v.capacity")
    List<Venue>findByCapacityIsGreaterThanEqual(@Param("max")int max);

    @Query("select v from Venue v where (v.capacity <:min) and (v.capacity)> :max")
    List<Venue>findByCapacityBetween(@Param("min")int min, @Param("max") int max);


    List<Venue> findAllBy();


    // aan een list kunt ge vragen .size en Iterable niet --> is per default

}
