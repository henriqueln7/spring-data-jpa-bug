package treino.springdatajpabug.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(
    value = """
    SELECT u.id, u.username, u.name
    FROM User u
    WHERE u.kind = 'ADMIN'
    UNION ALL
    SELECT u.id, u.username, u.name
    FROM User u
    WHERE u.kind = 'REGULAR'
    AND u.kind IN :#{#kinds.![name()]}
    """, countQuery = """
    SELECT count(1) FROM User u WHERE u.kind IN :#{#kinds.![name()]}
    """, nativeQuery = true)
    Page<List<UserDto>> findAllByKinds(List<UserKind> kinds, Pageable pageable);
}
