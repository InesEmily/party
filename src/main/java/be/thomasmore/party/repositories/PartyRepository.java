package be.thomasmore.party.repositories;

import be.thomasmore.party.model.Party;
import be.thomasmore.party.model.Venue;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;

public interface PartyRepository extends CrudRepository<Party, Integer> {

    Iterable<Party>findByVenue(Optional<Venue> venue);

}
