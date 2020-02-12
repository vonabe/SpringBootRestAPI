package ru.vonabe.filmsonline.api.entites.api.chat;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "messages", schema = "public", catalog = "filmsonlinechat_db")
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String userid;
    private Long channelid;
    @Size(max = 500)
    private String message;
    private String username;
    private LocalDateTime date;

    public MessageEntity() { }

    public MessageEntity(@Size(max = 500) String message) {
        this.message = message;
    }

}
