package hust.soict.dsai.aims.media;
import hust.soict.dsai.aims.media.PlayerException;
public class DigitalVideoDisc extends Disc implements Playable {
    
    private static int nbDigitalVideoDiscs = 0;

    // Streamlined Constructor using the parent Disc's constructor
    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        // Automatically increments the count and sets it as the ID
        super(++nbDigitalVideoDiscs, title, category, cost, length, director);
    }

    
    @Override
    public void play() throws PlayerException {
       
        if (this.getLength() <= 0) {
        	throw new hust.soict.dsai.aims.media.PlayerException("ERROR: DVD length is 0 or negative for item: " + this.getTitle());
        }
        
        System.out.println("Playing DVD: " + this.getTitle());
        System.out.println("DVD length: " + this.getLength());
    }
}