package ru.vonabe.filmsonline.api.exceptions;

public class NotfoundUserException extends RuntimeException{

    public NotfoundUserException(String message) {
        super("Not found user by id "+message);
    }
}
