package ru.vonabe.filmsonline.api.entites.api;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "token", schema = "public", catalog = "filmsonlinechat_db")
@Data
public class TokenEntity {

    @Id
    @Column(unique = true)
    private String userid;
    private String token;

    public TokenEntity() {}

    public TokenEntity(String userid, String token) {
        this.userid = userid;
        this.token = token;
    }
}

