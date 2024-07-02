package study.stepup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Converter {

    @Autowired
    List<ConverterAble> converters;

    public void convert() {
        System.out.println("convert");
        for(ConverterAble c : converters){
            c.convert();
        }
    }
}
