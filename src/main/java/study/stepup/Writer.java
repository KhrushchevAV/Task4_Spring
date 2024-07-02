package study.stepup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import study.stepup.db.Logins;
import study.stepup.db.LoginsRepository;
import study.stepup.db.Users;
import study.stepup.db.UsersRepository;

import java.sql.Timestamp;

@Component
public class Writer {
    @Autowired
    Model model;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    LoginsRepository loginsRepository;

    public void debugWrite() {
        System.out.println("write");
        System.out.println(model);
    }

    public void write() {
        System.out.println("write");
        // по всем строкам в модели
        for (DataString ds : model.lds) {
            Users user = usersRepository.getByUsername(ds.username);
            if (user == null) {
                // если такого пользователя еще небыло, создадим и сохраним
                user = new Users(ds.username, ds.fio);
                usersRepository.save(user);
            }
            Timestamp t;
            try {
                t = Timestamp.valueOf(ds.access_date);
            } catch (Exception e) {
                // классическая проблема. Надо знать в каком формате дата в исходном тексте. Если в нескольких - писать класс с их разбором.
                // что делать если таки не удалось разобрать? Как заказчик скажет. Например записать служебную дату одну для всех таких случаев.
                t = Timestamp.valueOf("2000-01-01 00:00:00");
            }
            // факты логинов сохраняем всегда. В задаче не оговорена уникальность.
            Logins log = new Logins(t, user.id, ds.application);
            loginsRepository.save(log);
        }
    }
}
