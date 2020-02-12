package ru.vonabe.filmsonline.api.entites.films;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "spr_name_ru")
public class SprNameRu {

  @Id
  private long id_kinopoisk;
  private String name_ru;

}
