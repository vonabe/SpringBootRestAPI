package ru.vonabe.filmsonline.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.vonabe.filmsonline.api.entites.api.PaginationFilms;
import ru.vonabe.filmsonline.api.entites.films.Film;
import ru.vonabe.filmsonline.api.services.FilmsService;

import java.util.List;

@RestController
@RequestMapping("/api/films")
public class FilmsController {

    @Autowired
    private FilmsService filmsRepository;

    @GetMapping
    public PaginationFilms getFilms(@RequestParam(defaultValue = "0") Integer nextPage,
                                    @RequestParam(defaultValue = "10") Integer offset) {
        List<Film> all = filmsRepository.findAll(nextPage, offset);
        return new PaginationFilms(String.format("http://onlineinteractivefilms.tk/api/films?nextPage=%1$s&offset=%2$s", nextPage + 1, offset),
                (nextPage != 0) ? String.format("http://onlineinteractivefilms.tk/api/films?nextPage=%1$s&offset=%2$s", nextPage - 1, offset) : null, all);
    }

    @GetMapping(value = "{id}")
    public Film findById(@PathVariable Long id) {
        Film film = filmsRepository.findById(id);
        return film;
    }

}
