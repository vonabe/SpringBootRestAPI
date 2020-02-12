package ru.vonabe.filmsonline.api.entites.films;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "spr_film_length")
public class SprFilmLength {

  @Id
  private long id_kinopoisk;
  private String film_length;

}
