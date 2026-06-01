package hust.soict.dsai.aims.media;

import java.util.ArrayList;
import java.util.List;


public class CompactDisc extends Disc implements Playable {
    private String artist;
    private List<Track> tracks = new ArrayList<Track>();

    // Constructor passing data down to Disc and Media
    public CompactDisc(int id, String title, String category, float cost, int length, String director, String artist) {
        super(id, title, category, cost, length, director);
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }

    // Track management methods
    public void addTrack(Track track) {
        if (!tracks.contains(track)) {
            tracks.add(track);
            System.out.println("Track \"" + track.getTitle() + "\" added to CD.");
        } else {
            System.out.println("Track \"" + track.getTitle() + "\" is already on this CD.");
        }
    }

    public void removeTrack(Track track) {
        if (tracks.contains(track)) {
            tracks.remove(track);
            System.out.println("Track \"" + track.getTitle() + "\" removed from CD.");
        } else {
            System.out.println("Track \"" + track.getTitle() + "\" not found on this CD.");
        }
    }

    
    @Override
    public int getLength() {
        int totalLength = 0;
        for (Track track : tracks) {
            totalLength += track.getLength();
        }
        return totalLength;
    }

    
    @Override
    public void play() throws PlayerException {
        if (this.getLength() <= 0) {
            throw new PlayerException("ERROR: CD length is 0 or negative for item: " + this.getTitle());
        }
        
        System.out.println("Playing CD: " + this.getTitle());
        System.out.println("CD Artist: " + this.getArtist());
        System.out.println("Total tracks runtime: " + this.getLength() + " mins");
        System.out.println("-----------------------------------");
        
        // Executes playback for every track inside the collection sequentially
        for (Track track : tracks) {
            track.play();
        }
    }
}