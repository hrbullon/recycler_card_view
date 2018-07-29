package view.recycler.card.com.recyclercardview.Models;

public class Movie {

    private String name;
    private int poster;

    public Movie() {}

    public Movie(String name, int poster) {
        this.name = name;
        this.poster = poster;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoster() {
        return poster;
    }

    public void setPoster(int img) {
        this.poster = img;
    }
}
