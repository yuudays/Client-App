package eltech.server;


import eltech.api.data.Movie;

import eltech.api.services.MovieService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MovieServiceImplementation implements MovieService {


    private static String login="postgres";
    private static String password="postgres";

    private static String url= "jdbc:postgresql://localhost/movies";

//    public static void main(String[] args) {
//        String sql = "CREATE TABLE movies ( id varchar(60) NOT NULL, name varchar(30) NOT NULL," + " director varchar(40) NOT NULL , description varchar(500) NOT NULL , PRIMARY KEY (id));";
//        Connection conn = null;
//        try {
//            conn = DriverManager.getConnection(url,login,password);
//            Statement statement = conn.createStatement();
//            statement.execute(sql);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
    @Override
    public List<Movie> getMovieList() {

        List<Movie> movies = new ArrayList<>();
        try {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            Connection conn = DriverManager.getConnection(url, login, password);
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select id,name,director,description from movies");
            while (resultSet.next()){
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String director = resultSet.getString("director");
                String description = resultSet.getString("description");
                Movie movie = new Movie();
                movie.setName(name);
                movie.setDirector(director);
                movie.setId(id);
                movie.setDescription(description);
                movies.add(movie);
            }
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return movies;
    }



//    @Override
//    public String addMovie(Movie movie) {
//        String id = Database.addMovie(movie);
//        return id;
//    }
    @Override
    public String addMovie(Movie movie){
        Connection conn = null;
        try {

            String id = UUID.randomUUID().toString();
            movie.setId(id);
            conn = DriverManager.getConnection(url, login, password);
            Statement statement = conn.createStatement();
            String sql = "INSERT INTO movies(id, name,director,description) VALUES (\'" +
                    movie.getId() + "\', \'" + movie.getName() + "\',\'"+ movie.getDirector() + "\',\'" +
                    movie.getDescription()+ "\')";
            statement.execute(sql);
            statement.close();
            conn.close();
            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void deleteMovie(String id) {
        try {
            Connection conn=DriverManager.getConnection(url,login,password);
            Statement statement = conn.createStatement();
            String sql = "DELETE FROM movies WHERE id='" + id + "'";
            statement.execute(sql);
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    @Override
//    public Movie getMovie(String id) {
//        return Database.getMovie(id);
//    }

    @Override
    public Movie getMovie(String id) {
        Connection conn = null;
        try {

            conn = DriverManager.getConnection(url, login, password);
            Statement statement = conn.createStatement();
            String sql = "SELECT name,director,description FROM movies WHERE id='" + id + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            Movie movie = null;
            if (resultSet.next()) {
                movie = new Movie();
                movie.setId(id);
                movie.setName(resultSet.getString("name"));
                movie.setDirector(resultSet.getString("director"));
                movie.setDescription(resultSet.getString("description"));
            }
            statement.close();
            conn.close();
            return movie;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void changeName(String id,String name) {
        Connection conn = null;
        try {

            conn = DriverManager.getConnection(url, login, password);
            Statement statement = conn.createStatement();
            String sql = "UPDATE movies SET name='"+name+"' WHERE id='" + id + "'";
            statement.execute(sql);
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void changeDirector(String id,String director) {
        Connection conn = null;
        try {

            conn = DriverManager.getConnection(url, login, password);
            Statement statement = conn.createStatement();
            String sql = "UPDATE movies SET director='"+director+"' WHERE id='" + id + "'";
            statement.execute(sql);
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void changeDescription(String id,String description) {
        Connection conn = null;
        try {

            conn = DriverManager.getConnection(url, login, password);
            Statement statement = conn.createStatement();
            String sql = "UPDATE movies SET description='"+description+"' WHERE id='" + id + "'";
            statement.execute(sql);
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Movie searchMovie(String name, String dir) {
        Connection conn = null;
        try {

            conn = DriverManager.getConnection(url, login, password);
            Statement statement = conn.createStatement();
            String sql = "SELECT id,description FROM movies WHERE name='" + name + "' " +
                    "AND director='"+dir+"'";
            ResultSet resultSet = statement.executeQuery(sql);
            Movie movie = null;
            if (resultSet.next()) {
                movie = new Movie();
                movie.setId(resultSet.getString("id"));
                movie.setName(name);
                movie.setDirector(dir);
                movie.setDescription(resultSet.getString("description"));
            }
            statement.close();
            conn.close();
            return movie;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
