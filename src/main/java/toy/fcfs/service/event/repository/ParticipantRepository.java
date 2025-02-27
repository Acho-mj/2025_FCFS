package toy.fcfs.service.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toy.fcfs.service.event.domain.Participant;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    boolean existsByEventIdAndUserId(Long eventId, Long userId);
}