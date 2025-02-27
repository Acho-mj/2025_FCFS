package toy.fcfs.service.event.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 이벤트명
    private String name;
    
    // 최대 참여 가능 인원
    private int maxParticipants;
    
    // 현재까지 참여한 인원
    private int currentParticipants;

    // 이벤트 시작 시간
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime startTime; 

    // 이벤트 오픈 시간 확인
    public boolean isOpen() {
        return LocalDateTime.now().isAfter(startTime);
    }

    // 선착순 인원 초과 여부 확인
    public boolean isFull() {
        return currentParticipants >= maxParticipants;
    }

    // 참여 인원 증가
    public void increaseParticipant() {
        if (!isFull()) {
            this.currentParticipants++;
        }
    }
}