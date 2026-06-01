package hust.soict.dsai.test.screen;
import hust.soict.dsai.aims.media.PlayerException;
import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Playable;
import java.awt.*;
import javax.swing.*;

public class MediaStore extends JPanel {
    private Media media; 
    private Cart cart;

    public MediaStore(Media media, Cart cart) {
        this.media = media;
        this.cart = cart;
        
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel(media.getTitle());
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel cost = new JLabel(String.format("%.2f $", media.getCost()));
        cost.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel container = new JPanel();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Add to Cart Button (Works out-of-the-box for Books, CDs, and DVDs!)
        JButton btnAddToCart = new JButton("Add to Cart");
        btnAddToCart.addActionListener(e -> {
            this.cart.addMedia(this.media);
            JOptionPane.showMessageDialog(this, 
                media.getTitle() + " has been added to your cart!", 
                "Cart Updated", JOptionPane.INFORMATION_MESSAGE);
        });
        container.add(btnAddToCart);

        // Conditional Play Button (Only render if the item is actually Playable)
        if (media instanceof Playable) {
            JButton btnPlay = new JButton("Play");
            btnPlay.addActionListener(e -> {
                try {
                    
                    ((Playable) media).play();
                    
                    
                    JOptionPane.showMessageDialog(this, "Playing: " + media.getTitle(), 
                                                  "AIMS Media Player", JOptionPane.INFORMATION_MESSAGE);
                    
                } catch (PlayerException ex) {
                    
                    JOptionPane.showMessageDialog(this, ex.getMessage(), 
                                                  "Media Playback Error", JOptionPane.ERROR_MESSAGE);
                }
            });
            container.add(btnPlay);
        }

        this.add(Box.createVerticalGlue());
        this.add(title);
        this.add(cost);
        this.add(Box.createVerticalGlue());
        this.add(container);
        
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
}