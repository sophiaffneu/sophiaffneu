/**
 * This class represents a position of a point.
 */
public class Point2D {
  private double x;
  private double y;

  /**
   * Construct the initial Point2D.
   *
   * @param x x of the position
   * @param y y of the position
   */
  public Point2D(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  @Override
  public String toString() {
    return "(" + this.getX() + "," + this.getY() + ")";
  }

}
