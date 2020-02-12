package ru.vonabe.filmsonline.api.entites.api;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "Users", schema = "public", catalog = "filmsonlinechat_db")
@Data
public class UserEntity {
//    String id = (String) map.get("id");
//    String email = (String) map.get("email");
//    String verified_email = (String) map.get("verified_email");
//    String name = (String) map.get("name");
//    String given_name = (String) map.get("given_name");
//    String family_name = (String) map.get("family_name");
//    String picture = (String) map.get("picture");
//    String locale = (String) map.get("locale");
    @Id
    @Column(unique = true)
    private String id;
    private String email;
    private Boolean verified_email;
    @Column(unique = true)
    private String username;
    private String name;
    private String given_name;
    private String family_name;
    private String picture;
    private String locale;
    private Integer rating = 0;
    private LocalDateTime last_visit;

}


