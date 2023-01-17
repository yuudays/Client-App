package eltech.api.services;

import eltech.api.data.Movie;

import java.io.Serializable;
import java.util.List;

public interface MovieService  extends Serializable  {
    List<Movie> getMovieList();

    String addMovie(Movie movie);

    void deleteMovie(String id);

    Movie getMovie(String id);

    void changeName(String id,String name);

    void changeDirector(String id,String director);

    void changeDescription(String id,String description);

    Movie searchMovie(String name,String dir);
}
