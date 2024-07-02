package study.stepup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Order(3)
public class Convert3_date implements ConverterAble{
    @Autowired
    Model model;

    String logName = "EmptyDate.log";

    // Промежуточная компонента проверки даты проверяет её наличие. Если дата не задана,
    // то человек не вносится в базу, а сведения о имени файла и значении человека заносятся в отдельный лог.
    @Override
    public void convert() {
        System.out.println(" Convert3_date.convert()");
        List<DataString> newLds = new ArrayList<>();
        for(DataString ds : model.lds) {
            if(ds.access_date.trim().isEmpty()) {
                putLog(ds);
                //model.lds.remove(ds);
            }
            else {
                newLds.add(ds);
            }
        }
        model.lds = newLds;
    }

    void putLog(DataString ds) {
        try {
            FileWriter fw = new FileWriter(logName);
            fw.write(ds.username + ";" + ds.fio + ";" + ds.access_date + ";" + ds.application);
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
