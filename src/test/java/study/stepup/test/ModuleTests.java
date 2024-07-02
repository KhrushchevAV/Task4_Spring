package study.stepup.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import study.stepup.*;


@SpringBootTest
public class ModuleTests {

    @Autowired
    Model model;

    @Autowired
    Convert1_fio c1;

    @Autowired
    Convert2_app c2;

    @Autowired
    Convert3_date c3;

    @BeforeAll
    public static void beforeAll() {
    }

    @BeforeEach
    public void beforeEach() {
        model.lds.clear();
        model.lds.add(new DataString("ivanov", "Иванов сергей сергеевич", "", "windows"));
    }

    @Test
    public void testConvert1() {
        c1.convert();
        System.out.println(model.lds.get(0));
        Assertions.assertTrue(model.lds.get(0).fio.contains("Сергей Сергеевич"));
    }

    @Test
    public void testConvert2() {
        c2.convert();
        System.out.println(model.lds.get(0));
        Assertions.assertTrue(model.lds.get(0).application.contains("other:windows"));
    }

    @Test
    public void testConvert3() {
        c3.convert();
        System.out.println(model.lds.size());
        Assertions.assertEquals(0, model.lds.size());
    }

}
