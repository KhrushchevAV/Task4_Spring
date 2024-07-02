package study.stepup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackages = "study.stepup")
public class Main {
    public static void main(String[] args) {
        String path = ".";
        // путь для сканирования файлов задан в командной строке
        if (args.length > 0) path = args[0];

        ApplicationContext ctx= SpringApplication.run(Main.class, args);

        Controller controller = ctx.getBean(Controller.class);
        controller.execute(path);
    }
}