package ru.vonabe.filmsonline.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.vonabe.filmsonline.api.entites.films.Film;
import ru.vonabe.filmsonline.api.services.FilmsService;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SpringBootTesting {

    @Autowired private FilmsService service;

    @Test
    public void whenInnerJoin(){
        List<Film> all = service.findAll();
        System.out.println("Test All: "+all);
    }

}
