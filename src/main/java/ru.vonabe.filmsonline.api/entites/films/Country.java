package ru.vonabe.filmsonline.api.entites.films;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "spr_country")
public class Country {

    @Id private Long id_kinopoisk;

    private String country;
}
