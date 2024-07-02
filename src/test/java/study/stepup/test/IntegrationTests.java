package study.stepup.test;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import study.stepup.Controller;
import study.stepup.db.Logins;
import study.stepup.db.LoginsRepository;
import study.stepup.db.Users;
import study.stepup.db.UsersRepository;

import java.util.List;

@SpringBootTest
public class IntegrationTests {

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine");

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    LoginsRepository loginsRepository;

    @Autowired
    Controller controller;

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.name", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Test
    public void testOk() {
        System.out.println("testOk");
    }

    @BeforeEach
    void setUp() {
        loginsRepository.deleteAll();
        loginsRepository.flush();
        usersRepository.deleteAll();
        usersRepository.flush();
        System.out.println("deleteAll");
    }

    @Test
    public void TestDB() {
        // загрузим текстовые файлы в текущем каталоге.
        // Файлы не менять. Ну или тест править!
        controller.execute(".");

        // должны получить 3 пользователя
        List<Users> u = usersRepository.findAll();
        Assertions.assertEquals(3, u.size());

        // должен быть пользователь user1
        Users user1 = usersRepository.getByUsername("user1");
        Assertions.assertNotNull(user1);

        // должно быть 3 записи о логинах
        List<Logins> l = loginsRepository.findAll();
        Assertions.assertEquals(3, l.size());

        // чисто посмотреть что там
        System.out.println(u);
        System.out.println(l);
    }
}
