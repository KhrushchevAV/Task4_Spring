package study.stepup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class Convert1_fio implements ConverterAble {
    @Autowired
    Model model;

    @Override
    // Промежуточная компонента проверки данных исправляет ФИО так, чтобы каждый его компонент начинался с большой буквы.
    public void convert() {
        System.out.println(" Convert1_fio.convert()");
        for(DataString ds : model.lds) {
            String newFio = "";
            String[] as = ds.fio.split(" ");
            for(String s : as) {
                if (s.isEmpty()) continue;
                newFio = newFio
                        + s.substring(0,1).toUpperCase()
                        + s.substring(1).toLowerCase()
                        + " ";
            }
            newFio = newFio.trim();
            ds.fio = newFio;
        }
    }
}
