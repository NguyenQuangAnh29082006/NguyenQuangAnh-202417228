package hust.soict.dsai.test.screen; 
import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.store.Store;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import java.awt.*;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.*;
import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.media.Track;
public class StoreScreen extends JFrame {
    private Store store;
    private Cart cart = new Cart(); // This keeps one continuous shopping session active
    
    public StoreScreen(Store store) {
        this.store = store;
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        cp.add(createNorth(), BorderLayout.NORTH);
        cp.add(createCenter(), BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("AIMS Store Catalog Dashboard");
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Creates the top Header and drop-down menu bar interface
    JPanel createNorth() {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        
        JMenuBar menuBar = new JMenuBar();
        JMenu menuOptions = new JMenu("Options");
        
        JMenu smUpdateStore = new JMenu("Update Store");
        
        // 1. Add Book Callback Handler
        JMenuItem mnuAddBook = new JMenuItem("Add Book");
        mnuAddBook.addActionListener(e -> {
            String title = JOptionPane.showInputDialog(this, "Enter Book Title:", "Add New Book", JOptionPane.QUESTION_MESSAGE);
            if (title != null && !title.trim().isEmpty()) {
                // Generates a random ID and passes it to your Book constructor
                int id = (int)(Math.random() * 1000) + 10;
                hust.soict.dsai.aims.media.Book newBook = new hust.soict.dsai.aims.media.Book(id, title.trim(), "Literature", 15.99f);
                this.store.addMedia(newBook);
                
                // Refreshes the window grid so the new item shows up instantly
                this.revalidate();
                this.repaint();
            }
        });
        smUpdateStore.add(mnuAddBook);
        
        // 2. Add CD Callback Handler
        JMenuItem mnuAddCD = new JMenuItem("Add CD");
        mnuAddCD.addActionListener(e -> {
            String title = JOptionPane.showInputDialog(this, "Enter CD Title:", "Add New Compact Disc", JOptionPane.QUESTION_MESSAGE);
            if (title != null && !title.trim().isEmpty()) {
                int id = (int)(Math.random() * 1000) + 10;
                hust.soict.dsai.aims.media.CompactDisc newCD = new hust.soict.dsai.aims.media.CompactDisc(id, title.trim(), "Music", 12.50f, 60, "Unknown Director", "Various Artists");
                this.store.addMedia(newCD);
                
                this.revalidate();
                this.repaint();
            }
        });
        smUpdateStore.add(mnuAddCD);
        
        // 3. Add DVD Callback Handler
        JMenuItem mnuAddDVD = new JMenuItem("Add DVD");
        mnuAddDVD.addActionListener(e -> {
            String title = JOptionPane.showInputDialog(this, "Enter DVD Title:", "Add New Digital Video Disc", JOptionPane.QUESTION_MESSAGE);
            if (title != null && !title.trim().isEmpty()) {
                hust.soict.dsai.aims.media.DigitalVideoDisc newDVD = new hust.soict.dsai.aims.media.DigitalVideoDisc(title.trim(), "Cinema", "Unknown Director", 120, 19.95f);
                this.store.addMedia(newDVD);
                
                this.revalidate();
                this.repaint();
            }
        });
        smUpdateStore.add(mnuAddDVD);
        
        menuOptions.add(smUpdateStore);
        
        // View Store menu item
        JMenuItem mnuViewStore = new JMenuItem("View Store");
        menuOptions.add(mnuViewStore);
        
        // View Cart menu item
        JMenuItem mnuViewCart = new JMenuItem("View Cart");
        mnuViewCart.addActionListener(e -> {
            new CartScreen(this.cart);
        });
        menuOptions.add(mnuViewCart);
        
        menuBar.add(menuOptions);
        north.add(menuBar);
        
        return north;
    }

    // Creates the grid tracking and rendering individual catalog item display cards
    JPanel createCenter() {
        JPanel center = new JPanel();
        
       
        java.util.ArrayList<hust.soict.dsai.aims.media.Media> mediaInStore = store.getItemsInStore();
        
        int numItems = mediaInStore.size();
        int rows = (int) Math.ceil(numItems / 3.0);
        center.setLayout(new java.awt.GridLayout(rows, 3, 15, 15));

       
        for (hust.soict.dsai.aims.media.Media media : mediaInStore) {
            MediaStore cell = new MediaStore(media, this.cart);
            center.add(cell);
        }

        return center;
    }

    public static void main(String[] args) {
        Store testStore = new Store();
        
        // Populate with mock items to verify responsive rendering grid behavior
        testStore.addMedia(new DigitalVideoDisc("The Matrix", "Sci-Fi", "Wachowskis", 136, 19.95f));
        testStore.addMedia(new DigitalVideoDisc("Inception", "Sci-Fi", "Nolan", 148, 24.99f));
        testStore.addMedia(new DigitalVideoDisc("Avatar", "Action", "Cameron", 162, 14.95f));
        testStore.addMedia(new DigitalVideoDisc("Gladiator", "History", "Scott", 155, 9.99f));
        testStore.addMedia(new DigitalVideoDisc("Test Broken Movie", "Horror", "Unknown", 0, 0.00f));
        testStore.addMedia(new Book(2, "Java Programming", "Education", 29.99f));
        
    
        CompactDisc cd = new CompactDisc(3, "Abbey Road", "Rock", 14.99f, 47, "The Beatles", "The Beatles");

        
        cd.addTrack(new Track("Come Together", 4));
        cd.addTrack(new Track("Something", 3));

      
        testStore.addMedia(cd);
        
        
        
        new StoreScreen(testStore);
    }
}