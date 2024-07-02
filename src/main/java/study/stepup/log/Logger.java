package study.stepup.log;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class Logger implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (!bean.getClass().isAnnotationPresent(LogTransformation.class))
            return bean;

        // Есть наша аннотация - создаем экземпляр "улучшателя" - Enhancer
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(bean.getClass());

        // добавляем обработчик, который будет перехватывать вызовы методов
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            // собственно перехват
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                Object returnValue = method.invoke(bean, args);
                // строки лога
                List<String> outStr = new ArrayList<>();
                outStr.add(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")) +
                        " " + obj.getClass() + "." + method.getName() + " (" + args + "); ");
                outStr.add(" return " + returnValue);
                // лог файл
                Path textFile = Paths.get(obj.getClass().getAnnotation(LogTransformation.class).value());
                Files.write(textFile, outStr, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                return returnValue;
            }
        });
        Constructor cons = bean.getClass().getConstructors()[0];
        Object [] arr=new Object[cons.getParameterCount()];
        // возвращаем экземпляр "улучшенного" бина
        return enhancer.create(cons.getParameterTypes(), arr);
    }
}
