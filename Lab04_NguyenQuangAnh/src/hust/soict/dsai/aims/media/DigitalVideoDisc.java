package hust.soict.dsai.aims.media;

public class DigitalVideoDisc extends Disc {
    
    
    private static int nbDigitalVideoDiscs = 0;

    // Streamlined Constructor using the parent Disc's constructor
    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        // Automatically increments the count and sets it as the ID
        super(++nbDigitalVideoDiscs, title, category, cost, length, director);
    }
}
