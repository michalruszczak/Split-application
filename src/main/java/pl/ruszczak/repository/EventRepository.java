package pl.ruszczak.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.ruszczak.model.Event;

@Repository
public interface EventRepository extends JpaRepository <Event, Long> {
    Event findByName(String name);
}
