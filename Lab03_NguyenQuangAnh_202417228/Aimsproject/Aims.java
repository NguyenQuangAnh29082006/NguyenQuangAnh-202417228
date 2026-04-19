package hust.soict.dsai.aims.aims;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.disc.DigitalVideoDisc;

public class Aims {
    public static void main(String[] args) {
        
        
        Cart anOrder = new Cart();

     
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", 
                "Animation", "Roger Allers", 87, 19.95f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", 
                "Science Fiction", "George Lucas", 87, 24.95f);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin", 
                "Animation", 18.99f);

       
        anOrder.addDigitalVideoDisc(dvd1, dvd2); 
        anOrder.addDigitalVideoDisc(dvd3);
        anOrder.print();
  
        System.out.println("\nTesting Search Methods:");
        
        
        anOrder.searchById(1);
        anOrder.searchById(10); 
        
      
        anOrder.searchByTitle("Star Wars");
        anOrder.searchByTitle("Frozen"); // Should say not found
    }
}