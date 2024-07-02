package study.stepup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class Convert2_app implements ConverterAble{
    @Autowired
    Model model;

    @Override
    // Промежуточная компонента проверяет что тип приложения соответствует одному из: “web”, “mobile”.
    // Если там записано что-либо иное, то оно преобразуется к виду “other:”+значение.
    public void convert() {
        System.out.println(" Convert2_app.convert()");
        for(DataString ds : model.lds) {
            if(!ds.application.equals("web") && !ds.application.equals("mobile")) {
                ds.application = "other:" + ds.application;
            }
        }
    }
}
