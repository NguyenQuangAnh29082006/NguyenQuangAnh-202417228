package hust.soict.dsai.aims.media;

public class Disc extends Media {
    private int length;
    private String director;

    // Constructor passing data to Media while handling its own fields
    public Disc(int id, String title, String category, float cost, int length, String director) {
        super(id, title, category, cost);
        this.length = length;
        this.director = director;
    }

    // Getters
    public int getLength() {
        return length;
    }

    public String getDirector() {
        return director;
    }
}