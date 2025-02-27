package toy.fcfs.presentation.controller.event;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toy.fcfs.service.event.EventService;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @PostMapping("/{eventId}/join")
    public ResponseEntity<String> joinEvent(
            @PathVariable Long eventId,
            @RequestParam(name = "userId") Long userId) {

        String response = eventService.joinEvent(eventId, userId);
        return ResponseEntity.ok(response);
    }
}