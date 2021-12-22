package shapes;

import java.util.ArrayList;

/**
 * Class to represent a shape.
 *
 * @author Bendeguz
 */
public abstract class Shape {

    protected final Point center;

    /**
     * This is the constructor of the Shape class.
     *
     * @param center Point which represents the center of the shape.
     */
    public Shape(Point center) {
        this.center = center;
    }

    /**
     * Method to get the Points of a square that is around the shape.
     *
     * @return 4 points representing a square.
     */
    public abstract ArrayList<Point> getCorners();
}
