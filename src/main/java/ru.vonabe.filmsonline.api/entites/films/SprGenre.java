package ru.vonabe.filmsonline.api.entites.films;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "spr_genre")
public class SprGenre {

    @Id
    private long id_kinopoisk;
    private String genre;

}
