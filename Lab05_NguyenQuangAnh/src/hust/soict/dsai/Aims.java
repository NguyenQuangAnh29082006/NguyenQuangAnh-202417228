package hust.soict.dsai;

import hust.soict.dsai.test.screen.StoreScreen;
import java.util.Scanner;
import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.*;
import hust.soict.dsai.aims.store.Store;

public class Aims {
    private static Store store = new Store();
    private static Cart cart = new Cart();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Pre-load some sample media items into the store inventory
        DigitalVideoDisc dvd = new DigitalVideoDisc("The Matrix", "Sci-Fi", "Wachowskis", 136, 19.95f);
        Book book = new Book(2, "Java Programming", "Education", 29.99f);
        book.addAuthor("James Gosling");
        
        CompactDisc cd = new CompactDisc(3, "Abbey Road", "Rock", 14.99f, 47, "The Beatles", "The Beatles");
        cd.addTrack(new Track("Come Together", 4));
        cd.addTrack(new Track("Something", 3));

        store.addMedia(dvd);
        store.addMedia(book);
        store.addMedia(cd);

        // Launch the visual storefront GUI framework framework
        new StoreScreen(store);

        // Launch the main CLI app loop console backup
        mainMenu();
    }

    // MAIN MENU
    public static void mainMenu() {
        while (true) {
            System.out.println("AIMS: ");
            System.out.println("--------------------------------");
            System.out.println("1. View store");
            System.out.println("2. Update store");
            System.out.println("3. See current cart");
            System.out.println("0. Exit");
            System.out.println("--------------------------------");
            System.out.print("Please choose a number: 0-1-2-3: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    store.printStore();
                    storeMenu();
                    break;
                case 2:
                    System.out.println("Feature expanding soon: Add/Remove items from store inventory manually.");
                    break;
                case 3:
                    cart.print();
                    cartMenu();
                    break;
                case 0:
                    System.out.println("Thank you for using AIMS. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void storeMenu() {
        while (true) {
            System.out.println("Options: ");
            System.out.println("--------------------------------");
            System.out.println("1. See a media's details");
            System.out.println("2. Add a media to cart");
            System.out.println("3. Play a media");
            System.out.println("4. See current cart");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.print("Please choose a number: 0-1-2-3-4: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter the title of the media: ");
                    String titleDetails = scanner.nextLine();
                    break;
                case 2:
                    System.out.print("Enter the title of the media to add: ");
                    String titleAdd = scanner.nextLine();
                    break;
                case 3:
                    System.out.print("Enter the title of the media to play: ");
                    String titlePlay = scanner.nextLine();
                    break;
                case 4:
                    cart.print();
                    cartMenu();
                    break;
                case 0:
                    return; 
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void cartMenu() {
        while (true) {
            System.out.println("Options: ");
            System.out.println("--------------------------------");
            System.out.println("1. Filter medias in cart");
            System.out.println("2. Sort medias in cart");
            System.out.println("3. Remove media from cart");
            System.out.println("4. Play a media");
            System.out.println("5. Place order");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.print("Please choose a number: 0-1-2-3-4-5: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Filter options: 1. By ID, 2. By Title");
                    break;
                case 2:
                    System.out.println("Sorting choices coming up in the next section of Lab 04!");
                    break;
                case 3:
                    System.out.print("Enter the title of the media to remove: ");
                    String titleRemove = scanner.nextLine();
                    break;
                case 4:
                    System.out.print("Enter the title of the media to play: ");
                    String titlePlayCart = scanner.nextLine();
                    break;
                case 5:
                    System.out.println("An order has been successfully placed! Your cart is now cleared.");
                    cart = new Cart(); 
                    return;
                case 0:
                    return; 
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}