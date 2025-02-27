package toy.fcfs.service.event;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import toy.fcfs.service.event.domain.Event;
import toy.fcfs.service.event.domain.Participant;
import toy.fcfs.service.event.repository.EventRepository;
import toy.fcfs.service.event.repository.ParticipantRepository;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final ParticipantRepository participantRepository;

    // 이벤트 정원이 다 차면 참여 불가 + 중복 참여 방지 + 성공일 경우 DB에 저장
    @Transactional
    public String joinEvent(Long eventId, Long userId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "이벤트가 없음"));

        if (!event.isOpen()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이벤트 시작 시간이 아님");
        }

        if (event.isFull()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "선착순 이벤트 인원 마감");
        }

        if (participantRepository.existsByEventIdAndUserId(eventId, userId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "중복 참여는 불가능");
        }

        // 참여 인원 증가
        event.increaseParticipant();
        eventRepository.save(event);

        // 참여자 저장
        Participant participant = new Participant();
        participant.setEvent(event);
        participant.setUserId(userId);
        participantRepository.save(participant);

        return "선착순 이벤트 참여 성공 !";
    }
}
