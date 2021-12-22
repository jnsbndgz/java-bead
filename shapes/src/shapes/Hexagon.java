package shapes;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class to represent a hexagon.
 *
 * @author Bendeguz
 */
public class Hexagon extends Polygon {

    /**
     * This is the constructor of the Hexagon class.
     *
     * @param center Point which represents the center of the polygon.
     * @param sideLength Value that represents the length of the polygon's
     * sides.
     */
    public Hexagon(Point center, double sideLength) {
        super(center, sideLength);
    }

    /**
     * This method returns half of the hexagon's height.
     *
     * @return Height of one of the triangles.
     */
    public double getHeight() {
        return ((Math.sqrt(3) / 2) * sideLength);
    }

    @Override
    public double getWidth() {
        return sideLength;
    }

    @Override
    public ArrayList<Point> getCorners() {
        return new ArrayList<>(Arrays.asList(new Point(center.getX() - getWidth(), center.getY() + getHeight()),
                new Point(center.getX() + getWidth(), center.getY() + getHeight()),
                new Point(center.getX() + getWidth(), center.getY() - getHeight()),
                new Point(center.getX() - getWidth(), center.getY() - getHeight())));
    }
}
