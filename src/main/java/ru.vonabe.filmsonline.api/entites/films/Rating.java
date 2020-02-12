package ru.vonabe.filmsonline.api.entites.films;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "spr_rating")
public class Rating {

    @Id private Long id_kinopoisk;

    private Float rating_kp;
    private Float rating_imdb;
    private Float votes_kp;
    private Float votes_imdb;

}
