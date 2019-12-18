package sample;

//Unchecked exception som
public class TooOldVideoException extends RuntimeException {

    public TooOldVideoException(int year){
        super("*** Videoer var ikke opfundet dengang!!! Dette år er ikke validt: " + year);

    }
}
