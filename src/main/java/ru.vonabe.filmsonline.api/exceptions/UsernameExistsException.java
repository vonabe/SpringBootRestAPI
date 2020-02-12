package ru.vonabe.filmsonline.api.exceptions;

public class UsernameExistsException extends RuntimeException {
    public UsernameExistsException(String message) {
        super("Username exists "+message);
    }
}
