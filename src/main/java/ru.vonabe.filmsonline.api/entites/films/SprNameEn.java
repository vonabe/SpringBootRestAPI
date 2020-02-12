package ru.vonabe.filmsonline.api.entites.films;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "spr_name_en")
public class SprNameEn {

  @Id
  private long id_kinopoisk;
  private String name_en;

}
