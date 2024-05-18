package main.exceptions;

public class BackwardsMoveNotAllowedException extends Exception{
    public BackwardsMoveNotAllowedException(String message) {
        super(message);
    }
}
