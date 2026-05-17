package hust.soict.dsai.test.passingparameter;

import hust.soict.dsai.aims.media.DigitalVideoDisc;

public class TestPassingParameter {

    public static void main(String[] args) {
    	DigitalVideoDisc jungleDVD = new DigitalVideoDisc("Jungle", "Maniac", "Director", 120, 19.95f);
    	DigitalVideoDisc cinderellaDVD = new DigitalVideoDisc("Cinderella", "Maniac", "Director", 120, 19.95f);

        System.out.println("--- INITIAL STATE ---");
        System.out.println("jungle dvd title: " + jungleDVD.getTitle());
        System.out.println("cinderella dvd title: " + cinderellaDVD.getTitle());

        //Wrong swap
        System.out.println("\n--- RUNNING WRONG SWAP ---");
        swap(jungleDVD, cinderellaDVD);
        System.out.println("jungle dvd title: " + jungleDVD.getTitle()); // Will still be "Jungle"
        System.out.println("cinderella dvd title: " + cinderellaDVD.getTitle());

        //Correct swap
        System.out.println("\n--- RUNNING CORRECT SWAP ---");
        correctSwap(jungleDVD, cinderellaDVD);
        System.out.println("jungle dvd title: " + jungleDVD.getTitle()); // Now "Cinderella"
        System.out.println("cinderella dvd title: " + cinderellaDVD.getTitle()); // Now "Jungle"
    }

    //Wrong method
    //It fails because it only swaps the local copies of the references.
    public static void swap(Object o1, Object o2) {
        Object tmp = o1;
        o1 = o2;
        o2 = tmp;
    }

    //Correct method
    //It works because it reaches inside the objects and swaps their actual data.
    public static void correctSwap(DigitalVideoDisc dvd1, DigitalVideoDisc dvd2) {
        
        String tmpTitle = dvd1.getTitle();
        dvd1.setTitle(dvd2.getTitle());
        dvd2.setTitle(tmpTitle);
        
      
        String tmpCategory = dvd1.getCategory();
        dvd1.setCategory(dvd2.getCategory());
        dvd2.setCategory(tmpCategory);
        
        
        float tmpCost = dvd1.getCost();
        dvd1.setCost(dvd2.getCost());
        dvd2.setCost(tmpCost);
        
        
    }
}