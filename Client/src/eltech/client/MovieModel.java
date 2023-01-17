package eltech.client;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
public class MovieModel<Mov> extends AbstractListModel<Mov> {

    private List<Mov> movieArrayList = new ArrayList<>();

    @Override
    public int getSize() {
        return movieArrayList.size();
    }

    @Override
    public Mov getElementAt(int index) {
        return movieArrayList.get(index);
    }

    public void setMovieList(List<Mov> list){
        movieArrayList = list;
        fireIntervalAdded(movieArrayList,0,movieArrayList.size());
    }

    public void addMovie(Mov movie){
        movieArrayList.add(movie);
        fireIntervalAdded(movie,movieArrayList.size()-1,movieArrayList.size()-1);
    }

    public void deleteMovie(Mov movie){
        movieArrayList.remove(movie);
        fireIntervalAdded(movie, movieArrayList.size(), movieArrayList.size());
    }

    public void setMovie(int index,Mov movie){
        movieArrayList.set(index,movie);
        fireContentsChanged(movie,index,index);
    }

    public int getIndex(Mov movie){
        for(int i=0;i<movieArrayList.size();i++){
            if(movie==movieArrayList.get(i))return i;
        }
        return -1;
    }
}
