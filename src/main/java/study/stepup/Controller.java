package study.stepup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Controller {
    @Autowired
    Model model;
    @Autowired
    Reader reader;
    @Autowired
    Converter converter;
    @Autowired
    Writer writer;

    public void execute(String path) {
        System.out.println("execute");

        reader.read(path);
        if (model.lds.size()==0) return;

        converter.convert();
        writer.write();
    }
}
