package study.stepup.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    // username - уникален для каждого юзера. Достаем по нему.
    //@Query("select u from users u where u.username = ?1 fetch first 1 rows only")
    Users getByUsername(String username);
}



