package shapes;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class to represent a circle.
 *
 * @author Bendeguz
 */
public class Circle extends Shape {

    private final double radius;

    /**
     * This is the constructor of the Circle class.
     *
     * @param center Point which represents the center of the polygon.
     * @param radius Value that represents the radius of the circle
     */
    public Circle(Point center, double radius) {
        super(center);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public ArrayList<Point> getCorners() {
        return new ArrayList<>(Arrays.asList(
                new Point(center.getX() - radius, center.getY() + radius),
                new Point(center.getX() + radius, center.getY() + radius),
                new Point(center.getX() + radius, center.getY() - radius),
                new Point(center.getX() - radius, center.getY() - radius)));
    }
}
