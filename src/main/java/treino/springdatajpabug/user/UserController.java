package treino.springdatajpabug.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public Page<List<UserDto>> getUsers(Optional<Integer> page, @RequestParam List<UserKind> kinds) {
        PageRequest pageable = PageRequest.of(page.orElse(0), 10);
        return userRepository.findAllByKinds(kinds, pageable);
    }

}
