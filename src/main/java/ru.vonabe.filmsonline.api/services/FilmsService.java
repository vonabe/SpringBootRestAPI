package ru.vonabe.filmsonline.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vonabe.filmsonline.api.entites.films.Film;
import ru.vonabe.filmsonline.api.films.repository.FilmsRepository;

import java.util.List;

@Service
@Transactional("db2TransactionManager")
public class FilmsService {

    @Autowired private FilmsRepository filmsRepository;

    public Film findById(Long id){
        Film film = filmsRepository.findById(id).get();
        return film;
    }

    public List<Film> findAll(){
        List<Film> films = filmsRepository.findAll();
        return films;
    }

    public List<Film> findAll(int page, int offset){
        List<Film> films = filmsRepository.findAll(PageRequest.of(page, offset)).toList();
        return films;
    }

}
