package study.stepup.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginsRepository extends JpaRepository<Logins, Long> {
}
