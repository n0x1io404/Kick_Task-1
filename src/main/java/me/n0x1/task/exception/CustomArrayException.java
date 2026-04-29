package me.n0x1.task.exception;

public class CustomArrayException extends Exception {
    public CustomArrayException(String massage, Throwable cause){
        super(massage, cause);
    }

    public CustomArrayException(String massage){
        super(massage);
    }

    public CustomArrayException(){
        super();
    }
}
