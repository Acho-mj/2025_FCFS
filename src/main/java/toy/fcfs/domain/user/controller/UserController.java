package toy.fcfs.domain.user.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import toy.fcfs.domain.user.entity.User;
import toy.fcfs.domain.user.repository.UserRepository;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserRepository userRepository;

    @PostMapping("/register")
    public Long register(@RequestParam(name = "username") String username) {
        User user = userRepository.save(new User(username));
        return user.getId();
    }

    @PostMapping("/login")
    public Long login(@RequestParam(name = "username") String username) {
        return userRepository.findByUsername(username)
                .map(User::getId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }
}
