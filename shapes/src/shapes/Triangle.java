package shapes;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class to represent a triangle.
 *
 * @author Bendeguz
 */
public class Triangle extends Polygon {

    /**
     * This is the constructor of the Triangle class.
     *
     * @param center Point which represents the center of the polygon.
     * @param sideLength Value that represents the length of the polygon's
     * sides.
     */
    public Triangle(Point center, double sideLength) {
        super(center, sideLength);
    }

    /**
     * This method return half of the triangle's height.
     *
     * @return Height of one of the triangle.
     */
    public double getHeight() {
        return (((Math.sqrt(3) / 2) * sideLength) / 2);
    }

    @Override
    public double getWidth() {
        return (sideLength / 2);
    }

    @Override
    public ArrayList<Point> getCorners() {
        return new ArrayList<>(Arrays.asList(new Point(center.getX() - getWidth(), center.getY() + getHeight()),
                new Point(center.getX() + getWidth(), center.getY() + getHeight()),
                new Point(center.getX() + getWidth(), center.getY() - getHeight()),
                new Point(center.getX() - getWidth(), center.getY() - getHeight())));
    }
}
