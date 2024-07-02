package study.stepup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@Component
public class Reader {
    @Autowired
    Model model;
    String delimeter = ";";

    public void read(String path) {
        System.out.println("read("+path+")");
        if (path.isEmpty()) path = ".";

        // список файлов
        File[] files = (new File(path).listFiles());
        for (File file : files) {
            String name = file.getName();
            if (!name.toLowerCase().endsWith(".txt")) continue;
            // читаем из каждого файла
            readFromFile(file);
        }
        System.out.println("readed " + model.lds.size() + " lines");
    }

    void readFromFile(File file) {
        try {
            Scanner sc = new Scanner(file, StandardCharsets.UTF_8);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] sl = line.split(delimeter);
                if (sl.length >= 3) {
                    model.lds.add(new DataString(sl[0], sl[1], sl[2], sl[3]));
                }
            }
            sc.close();
        } catch (Exception e) {
            System.out.println(file.getName() + " " + e);
        }
    }
}
