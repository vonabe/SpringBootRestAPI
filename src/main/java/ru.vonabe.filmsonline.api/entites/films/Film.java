package ru.vonabe.filmsonline.api.entites.films;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "films_all")
@SecondaryTables({
        @SecondaryTable(name = "spr_country", pkJoinColumns = @PrimaryKeyJoinColumn(name = "id_kinopoisk")),
        @SecondaryTable(name = "spr_name_ru", pkJoinColumns = @PrimaryKeyJoinColumn(name = "id_kinopoisk")),
        @SecondaryTable(name = "spr_name_en", pkJoinColumns = @PrimaryKeyJoinColumn(name = "id_kinopoisk")),
        @SecondaryTable(name = "spr_genre", pkJoinColumns = @PrimaryKeyJoinColumn(name = "id_kinopoisk")),
        @SecondaryTable(name = "spr_year", pkJoinColumns = @PrimaryKeyJoinColumn(name = "id_kinopoisk")),
        @SecondaryTable(name = "spr_film_length", pkJoinColumns = @PrimaryKeyJoinColumn(name = "id_kinopoisk")),
        @SecondaryTable(name = "spr_rating", pkJoinColumns = @PrimaryKeyJoinColumn(name = "id_kinopoisk"))
})
public class Film {

    @Id
    private Long id_kinopoisk;

    @Column(name = "name_ru", table = "spr_name_ru")
    private String name_ru;

    @Column(name = "name_en", table = "spr_name_en")
    private String name_en;

    @Column(name = "country", table = "spr_country")
    private String country;

    @Column(name = "genre", table = "spr_genre")
    private String genre;

    @Column(name = "year", table = "spr_year")
    private Integer year;

    @Column(name = "film_length", table = "spr_film_length")
    private String film_length;

    @Column(name = "rating_kp", table = "spr_rating")
    private float rating_kp;

    public Film() { }

    @Override
    public String toString() {
        return "Film{" +
                "id_kinopoisk=" + id_kinopoisk +
                ", name_ru='" + name_ru + '\'' +
                ", name_en='" + name_en + '\'' +
                ", country='" + country + '\'' +
                ", genre='" + genre + '\'' +
                ", year=" + year +
                ", film_length='" + film_length + '\'' +
                ", rating_kp=" + rating_kp +
                '}';
    }

}

