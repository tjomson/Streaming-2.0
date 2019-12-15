package sample;

public class loggedInAsGuestException extends Exception {

    String message;

    public loggedInAsGuestException(String message) {
        this.message = message;
    }

}
