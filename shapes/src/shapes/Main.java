package shapes;

import java.io.FileNotFoundException;

/**
 * The main part of the project designed to test it.
 *
 * @author Bendeguz
 */
public class Main {

    public static void main(String[] args) {
        Space space1 = new Space();
        Space space2 = new Space();
        Space space3 = new Space();
        
        try {
            space1.read("test#3.txt");
            space2.read("test#2.txt");
            space3.read("test#5.txt");
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
            System.exit(-1);
        } catch (InvalidInputException ex) {
            System.out.println("Invalid input!");
            System.exit(-1);
        }
        
        System.out.println(space1);
        System.out.println(space2);
        System.out.println(space3);

    }

}
