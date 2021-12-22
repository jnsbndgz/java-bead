package shapes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Bendeguz
 */
public class Space {

    private final ArrayList<Shape> shapes;

    /**
     * This is the constructor of a 2d space.
     */
    public Space() {
        this.shapes = new ArrayList<>();
    }

    /**
     * This is the constructor of a 2d space.
     *
     * @param shapes You can pass in already existing collection of shapes.
     */
    public Space(ArrayList<Shape> shapes) {
        this.shapes = shapes;
    }

    /**
     * This method is supposed to populate the space with shapes.
     *
     * @param input This should be the name of the file you store the shapes in.
     * @throws FileNotFoundException You get this exception if the given file is
     * not existing.
     * @throws InvalidInputException You get this exception if the format of the
     * given file is incorrect.
     */
    public void read(String input) throws FileNotFoundException, InvalidInputException {
        Scanner sc = new Scanner(new BufferedReader(new FileReader(input)));
        if (!sc.hasNext() || sc.nextInt() == 0) {
            throw new InvalidInputException();
        }
        while (sc.hasNext()) {
            Shape shape;

            String kind = sc.next();
            Point center = new Point(sc.nextDouble(), sc.nextDouble());
            double n = sc.nextDouble();

            switch (kind) {
                case "C" ->
                    shape = new Circle(center, n);
                case "T" ->
                    shape = new Triangle(center, n);
                case "S" ->
                    shape = new Square(center, n);
                case "H" ->
                    shape = new Hexagon(center, n);
                default ->
                    throw new InvalidInputException();
            }

            shapes.add(shape);
        }
    }

    /**
     * This method gives you the (S)ize (O)f (T)he (S)mallest (R)ectangle (T)hat
     * (C)an (C)over all the shapes in the space.
     *
     * @return The size of the rectangle.
     */
    public double getSOSRTCC() {
        double topLeftX = shapes.get(0).getCorners().get(0).getX(),
                topLeftY = shapes.get(0).getCorners().get(0).getY(),
                bottomRightX = shapes.get(0).getCorners().get(2).getX(),
                bottomRightY = shapes.get(0).getCorners().get(2).getY();

        for (Shape sh : shapes) {
            for (Point p : sh.getCorners()) {
                if (p.getX() < topLeftX) {
                    topLeftX = p.getX();
                }
                if (p.getY() > topLeftY) {
                    topLeftY = p.getY();
                }
                if (p.getX() > bottomRightX) {
                    bottomRightX = p.getX();
                }
                if (p.getY() < bottomRightY) {
                    bottomRightY = p.getY();
                }
            }
        }

        return Math.abs(topLeftX - bottomRightX) * Math.abs(topLeftY - bottomRightY);
    }

    /**
     * This method returns with a nicely detailed string.
     *
     * @return Nicely detailed report.
     */
    @Override
    public String toString() {
        return "The area of the smallest rectangle that can cover every shape in the space is: " + getSOSRTCC();
    }

}
