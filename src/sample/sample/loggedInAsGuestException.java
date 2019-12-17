package sample;

public class loggedInAsGuestException extends Exception {

    private String message;

    public loggedInAsGuestException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
