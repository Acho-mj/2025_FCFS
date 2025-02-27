package toy.fcfs.service.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toy.fcfs.service.event.domain.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
