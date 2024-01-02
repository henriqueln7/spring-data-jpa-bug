package treino.springdatajpabug.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private UserKind kind;

    @Deprecated
    protected User(){}

    public User(String username, String email, UserKind kind) {
        this.username = username;
        this.email = email;
        this.kind = kind;
    }
}
