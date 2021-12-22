package shapes;

/**
 * Class to represent a polygon.
 *
 * @author Bendeguz
 */
public abstract class Polygon extends Shape {

    protected final double sideLength;

    /**
     * This is the constructor of the Polygon class.
     *
     * @param center Point which represents the center of the polygon.
     * @param sideLength Value that represents the length of the polygon's
     * sides.
     */
    public Polygon(Point center, double sideLength) {
        super(center);
        this.sideLength = sideLength;
    }

    public abstract double getWidth();

}
