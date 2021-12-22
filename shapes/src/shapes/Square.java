package shapes;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class to represent a square.
 *
 * @author Bendeguz
 */
public class Square extends Polygon {

    /**
     * This is the constructor of the Square class.
     *
     * @param center Point which represents the center of the polygon.
     * @param sideLength Value that represents the length of the polygon's
     * sides.
     */
    public Square(Point center, double sideLength) {
        super(center, sideLength);
    }

    @Override
    public double getWidth() {
        return (sideLength / 2);
    }

    @Override
    public ArrayList<Point> getCorners() {
        return new ArrayList<>(Arrays.asList(new Point(center.getX() - getWidth(), center.getY() + getWidth()),
                new Point(center.getX() + getWidth(), center.getY() + getWidth()),
                new Point(center.getX() + getWidth(), center.getY() - getWidth()),
                new Point(center.getX() - getWidth(), center.getY() - getWidth())));
    }
}
