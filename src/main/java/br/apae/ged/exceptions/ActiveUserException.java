package br.apae.ged.exceptions;

public class ActiveUserException extends RuntimeException{

    public ActiveUserException(String message){
        super(message);
    }
}
