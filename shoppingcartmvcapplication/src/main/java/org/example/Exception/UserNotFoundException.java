package org.example.Exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException (String massage) {
        super(massage);
    }
}
