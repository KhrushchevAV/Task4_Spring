package study.stepup;

import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@ToString
public class Model {
    public List<DataString> lds = new ArrayList<>();
}
