package sample;

//Checked exception som kastes hvis video ikke opfylder de givne kriterier.
public class noSuchVideoException extends Exception {

    private String message;

    // Har lavet konstruktøren med en variabel besked, så kan vi customize den alt afhængig af hvor vi bruger den.
    public noSuchVideoException(String message) {
           this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
