package ru.vonabe.filmsonline.api.films.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vonabe.filmsonline.api.entites.films.Film;

@Repository
public interface FilmsRepository extends JpaRepository<Film, Long> {

//    @Query("SELECT " +
//        "new ru.vonabe.filmsonline.api.entites.films.Film(ik1.id_kinopoisk, n1.name_ru, n2.name_en, c1.country, g1.genre, y1.year, fl1.film_length, rk1.rating)" +
//        " FROM films_all f1" +
//        " INNER JOIN spr_id_kinopoisk ik1 ON f1.id_kinopoisk = ik1.id_kinopoisk" +
//        " INNER JOIN spr_name_ru n1 ON f1.id_kinopoisk = n1.id_kinopoisk" +
//        " INNER JOIN spr_name_en n2 ON f1.id_kinopoisk = n2.id_kinopoisk" +
//        " INNER JOIN spr_country c1 ON f1.id_kinopoisk = c1.id_kinopoisk" +
//        " INNER JOIN spr_genre g1 ON f1.id_kinopoisk = g1.id_kinopoisk" +
//        " INNER JOIN spr_year y1 ON f1.id_kinopoisk = y1.id_kinopoisk" +
//        " INNER JOIN spr_film_length fl1 ON f1.id_kinopoisk = fl1.id_kinopoisk" +
//        " INNER JOIN spr_rating rk1 ON f1.id_kinopoisk = rk1.id_kinopoisk")
//    @Query("SELECT" +
//            " ik1.id_kinopoisk as id_kinopoisk," +
//            " n1.name_ru," +
//            " n2.name_en," +
//            " c1.country," +
//            " g1.genre," +
//            " y1.year," +
//            " fl1.film_length," +
//            " rk1.rating_kp" +
//            " FROM films_all f1" +
//            " INNER JOIN spr_id_kinopoisk ik1 ON f1.id_kinopoisk = ik1.id_kinopoisk" +
//            " INNER JOIN spr_name_ru n1 ON f1.id_kinopoisk = n1.id_kinopoisk" +
//            " INNER JOIN spr_name_en n2 ON f1.id_kinopoisk = n2.id_kinopoisk" +
//            " INNER JOIN spr_country c1 ON f1.id_kinopoisk = c1.id_kinopoisk" +
//            " INNER JOIN spr_genre g1 ON f1.id_kinopoisk = g1.id_kinopoisk" +
//            " INNER JOIN spr_year y1 ON f1.id_kinopoisk = y1.id_kinopoisk" +
//            " INNER JOIN spr_film_length fl1 ON f1.id_kinopoisk = fl1.id_kinopoisk" +
//            " INNER JOIN spr_rating rk1 ON f1.id_kinopoisk = rk1.id_kinopoisk"
//    )
//    List<Film> findAllFilms();
}
