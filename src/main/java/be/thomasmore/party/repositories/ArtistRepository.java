package be.thomasmore.party.repositories;

import be.thomasmore.party.model.Artist;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArtistRepository extends CrudRepository<Artist,Integer> {
    @Query("select  a from Artist a where upper(a.artistName) like%:keyword%")
    Iterable<Artist>findByArtistName(@Param("keyword")String keyword);
    List<Artist> findByArtistNameContainingIgnoreCase(String keyword);
    List<Artist> findAllBy();
}
