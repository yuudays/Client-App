package eltech.api.data;

import java.io.Serializable;

public class Movie implements Serializable {
    private String id;
    private String name;
    private String director;
    private String description;

    public Movie(String name){
        this.name=name;
    }

    public Movie() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
