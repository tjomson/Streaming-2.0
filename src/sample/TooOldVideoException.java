package sample;

//Unchecked exception som kastes når en video er for gammel.
public class TooOldVideoException extends RuntimeException {

    public TooOldVideoException(int year){
        super("*** Videoer var ikke opfundet dengang!!! Dette år er ikke tilladt: " + year);

    }
}
