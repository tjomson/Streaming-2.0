package project;

import java.io.IOException;
import java.lang.System;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Assert;
import org.junit.Test;

public class TestMovieReader extends TestCase {

    Model model;

    @Test
    public void testMovieReaderSize() throws IOException {
        model = Model.getInstance();
        MovieReader m = new MovieReader();;
        assertEquals( m.readMovies("film.txt").size(), model.getMovies().size());
    }
    @Test
    public void testLoggedInAsGuestException()  {
        try {
            model = Model.getInstance();
            model.setUsername(0, "gæst");
            SearchEngine s = new SearchEngine();
            s.getSearchItems(null, null, null, false, false, true);
        } catch (loggedInAsGuestException | IOException | noSuchVideoException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testNoSuchVideoException() {
        try {
            model = Model.getInstance();
            model.setUsername(1, "Kir");
            SearchEngine x = new SearchEngine();
            x.getSearchItems("har", "Titel: A-Å", "Alle", true, false, false);
        } catch (loggedInAsGuestException| IOException | noSuchVideoException e) {
            System.out.println(e.getMessage());
        }
    }

}
