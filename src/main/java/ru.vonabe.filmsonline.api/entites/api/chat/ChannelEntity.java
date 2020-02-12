package ru.vonabe.filmsonline.api.entites.api.chat;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "channels", schema = "public", catalog = "filmsonlinechat_db")
public class ChannelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String userid;
    private boolean isOnline = false;
    private String idkinopoisk;
    private String title;

    public ChannelEntity(String userid, boolean isOnline, String idkinopoisk, String title) {
        this.userid = userid;
        this.isOnline = isOnline;
        this.idkinopoisk = idkinopoisk;
        this.title = title;
    }
}
