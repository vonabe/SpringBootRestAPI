package ru.vonabe.filmsonline.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vonabe.filmsonline.api.entites.api.TokenEntity;
import ru.vonabe.filmsonline.api.entites.api.UserEntity;
import ru.vonabe.filmsonline.api.exceptions.NotfoundUserException;
import ru.vonabe.filmsonline.api.services.TokenServices;
import ru.vonabe.filmsonline.api.services.UserServices;
import ru.vonabe.filmsonline.api.utils.JwtTokenUtil;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserServices userService;

    @Autowired
    private TokenServices tokenService;

    @GetMapping(value = "/user")
    public String user(Model model, Authentication authentication) {
        UserEntity userEntity = (UserEntity) authentication.getPrincipal();
        String token = JwtTokenUtil.generateToken(userEntity);
        model.addAttribute("name", userEntity.getName());
        model.addAttribute("token", token);
        tokenService.save(new TokenEntity(userEntity.getId(), token));
        return "welcome";
    }

    @GetMapping(value = "/current")
    public ResponseEntity<?> getCurrentUser(@RequestHeader("Authorization") String token){
        token = JwtTokenUtil.formatToken(token);
        TokenEntity byToken = tokenService.findByToken(token);
        if(byToken != null){
            return ResponseEntity.ok(userService.findById(byToken.getUserid()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User by token not found");
    }

    @PostMapping(value = {"/username"}, headers = {"Authorization"})
    public ResponseEntity<?> setUsername(@RequestHeader("Authorization") String token, @RequestParam(value = "username") String username) {
        try {
            if(username!=null && !username.isEmpty() && username.length() >= 4) {
                if (userService.existsByUsername(username)) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("Username is busy");
                } else {
                    TokenEntity tokenEntity = tokenService.findByToken(JwtTokenUtil.formatToken(token));
                    UserEntity userEntity = userService.findById(tokenEntity.getUserid());
                    userEntity.setUsername(username);
                    userService.save(userEntity);
                    return ResponseEntity.ok().build();
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("invalid username");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.valueOf(422)).body(e.getMessage());
        }
    }

    @RequestMapping(value = {"/time"})
    public ResponseEntity<?> getTime() {
        return new ResponseEntity<>(String.valueOf(System.currentTimeMillis()), HttpStatus.FOUND);
    }

    @RequestMapping(value = {"/getUser/{id}"}, headers = {"Authorization"})
    public ResponseEntity<UserEntity> getUser(@PathVariable String id) {
        UserEntity userEntity = userService.findById(id);
        if (userEntity == null) throw new NotfoundUserException(id);
        return new ResponseEntity<>(userEntity, HttpStatus.OK);
    }

}
