package project;

//Unchecked exception som kastes hvis en video har forkerte parametre.
public class InvalidVideoException extends RuntimeException {

    public InvalidVideoException(int year, double rating){
        super("*** De indtastede oplysninger, kan ikke bruges til et videoobjekt. Oplysningerne er: " + year + " " + rating);

    }
}
