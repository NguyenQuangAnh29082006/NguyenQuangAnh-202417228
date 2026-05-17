package hust.soict.dsai.aims.store;

import java.util.ArrayList;
import hust.soict.dsai.aims.media.Media;

public class Store {
    // The collection holding all available items in the shop inventory
    private ArrayList<Media> itemsInStore = new ArrayList<Media>();

    // Add an item to the store inventory
    public void addMedia(Media media) {
        if (!itemsInStore.contains(media)) {
            itemsInStore.add(media);
            System.out.println("The item \"" + media.getTitle() + "\" has been added to the store.");
        } else {
            System.out.println("The item \"" + media.getTitle() + "\" is already in the store.");
        }
    }

    // Remove an item from the store inventory
    public void removeMedia(Media media) {
        if (itemsInStore.contains(media)) {
            itemsInStore.remove(media);
            System.out.println("The item \"" + media.getTitle() + "\" has been removed from the store.");
        } else {
            System.out.println("The item \"" + media.getTitle() + "\" was not found in the store.");
        }
    }

    // Helper method to look at what's currently on the shelves
    public void printStore() {
        System.out.println("\n============= HUST MEDIA STORE =============");
        if (itemsInStore.isEmpty()) {
            System.out.println("[The store inventory is currently empty]");
        } else {
            for (int i = 0; i < itemsInStore.size(); i++) {
                Media media = itemsInStore.get(i);
                System.out.println((i + 1) + ". [" + media.getCategory() + "] " + media.getTitle() + ": " + media.getCost() + "$");
            }
        }
        System.out.println("============================================\n");
    }

    // Getter to let our main app access the items for searching later
    public ArrayList<Media> getItemsInStore() {
        return itemsInStore;
    }
}