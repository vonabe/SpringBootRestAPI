package ru.vonabe.filmsonline.api.entites.api;

import lombok.Data;
import ru.vonabe.filmsonline.api.entites.films.Film;

import java.util.List;

@Data
public class PaginationFilms {

    private String nextPage;
    private String backPage;

    private List<Film> films;

    public PaginationFilms(String nextPage, String backPage, List<Film> films) {
        this.nextPage = nextPage;
        this.backPage = backPage;
        this.films = films;
    }
}
