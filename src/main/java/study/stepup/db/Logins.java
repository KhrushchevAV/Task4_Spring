package study.stepup.db;

import jakarta.persistence.*;
import lombok.ToString;

import java.sql.Timestamp;

@Entity
@ToString
public class Logins {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id",unique=true, nullable = false)
    public Long id;
    public Timestamp access_date;
    public Long user_id;
    public String application;

    public Logins() {
    }

    public Logins(Timestamp access_date, Long user_id, String application) {
        this.access_date = access_date;
        this.user_id = user_id;
        this.application = application;
    }
}
