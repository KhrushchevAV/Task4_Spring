package study.stepup.db;

import jakarta.persistence.*;
import lombok.ToString;

@Entity
@ToString
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id",unique=true, nullable = false)
    public Long id;
    public String username;
    public String fio;

    public Users() {
    }

    public Users(String username, String fio) {
        this.username = username;
        this.fio = fio;
    }
}
