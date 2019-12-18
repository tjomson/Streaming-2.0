package project;

//Exception som kastes hvis man er logget ind som gæst og prøver at se Min Liste.
public class loggedInAsGuestException extends Exception {

    private String message;

    public loggedInAsGuestException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
