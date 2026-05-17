package hust.soict.dsai.aims.cart;

import java.util.ArrayList;
import hust.soict.dsai.aims.media.Media;

public class Cart {
    public static final int MAX_NUMBERS_ORDERED = 20;
    
   
    private ArrayList<Media> itemsOrdered = new ArrayList<Media>();

    // Add a single media item (Book, CD, or DVD)
    public void addMedia(Media media) {
        if (itemsOrdered.size() < MAX_NUMBERS_ORDERED) {
            if (!itemsOrdered.contains(media)) {
                itemsOrdered.add(media);
                System.out.println("The media \"" + media.getTitle() + "\" has been added.");
            } else {
                System.out.println("The media \"" + media.getTitle() + "\" is already in the cart.");
            }
        } else {
            System.out.println("The cart is full.");
        }
    }

    // Remove a media item (ArrayList handles the data shifting automatically!)
    public void removeMedia(Media media) {
        if (itemsOrdered.contains(media)) {
            itemsOrdered.remove(media);
            System.out.println("The media \"" + media.getTitle() + "\" has been removed.");
        } else {
            System.out.println("The media \"" + media.getTitle() + "\" was not found in the cart.");
        }
    }

    // Calculates total cost of all mixed media items
    public float totalCost() {
        float total = 0;
        for (Media media : itemsOrdered) {
            total += media.getCost();
        }
        return total;
    }

    // Prints the list of ordered items
    public void print() {
        System.out.println("***********************CART***********************");
        System.out.println("Ordered Items:");
        for (int i = 0; i < itemsOrdered.size(); i++) {
            Media media = itemsOrdered.get(i);
            // Polymorphically accesses the title, category, and cost of any Media type
            System.out.println((i + 1) + ". Media - " + media.getTitle() + " - " 
                + media.getCategory() + ": " + media.getCost() + " $");
        }
        System.out.println("Total cost: " + totalCost() + " $");
        System.out.println("***************************************************");
    }

    // Search by ID
    public void searchById(int id) {
        boolean found = false;
        for (Media media : itemsOrdered) {
            if (media.getId() == id) {
                System.out.println("Found match for ID " + id + ": " + media.getTitle());
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("No item found with ID: " + id);
        }
    }

    // Search by Title
    public void searchByTitle(String title) {
        boolean found = false;
        for (Media media : itemsOrdered) {
            if (media.getTitle().equalsIgnoreCase(title)) {
                System.out.println("Found match for title \"" + title + "\": " + media.getTitle());
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("No item found with title: " + title);
        }
    }
}