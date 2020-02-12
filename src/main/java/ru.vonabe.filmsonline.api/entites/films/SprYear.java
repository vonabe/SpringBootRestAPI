package ru.vonabe.filmsonline.api.entites.films;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "spr_year")
public class SprYear {
    @Id
    private long id_kinopoisk;
    private long year;

}
