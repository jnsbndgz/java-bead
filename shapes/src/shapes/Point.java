package shapes;

/**
 *
 * @author Bendeguz
 */
public class Point {

    private final double x, y;

    /**
     * Constructor of the Point class.
     *
     * @param x Represents the X coordinate.
     * @param y Represents the Y coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Method to get the Point's X coordinate.
     *
     * @return Value of the X coordinate.
     */
    public double getX() {
        return x;
    }

    /**
     * Method to get the Point's Y coordinate.
     *
     * @return Value of the Y coordinate.
     */
    public double getY() {
        return y;
    }
}
