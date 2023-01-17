package eltech.client;

import eltech.api.data.Movie;

import javax.swing.*;
import java.awt.*;

public class MovieList extends JList<Movie> {
    public MovieList() {
        super(new MovieModel<>());
        setCellRenderer(new MovieRenderer());
    }

    public MovieModel getMovieModel() {
        return (MovieModel) getModel();
    }
}
