/**
 * This class represents a position of a point.
 */
public class Point2D {
  private double X;
  private double Y;

  public Point2D(double x, double y) {
    //here will throw an IlligleArgumentException of the canvas only accept positive values and
    //negative values passed in.
    this.X = x;
    this.Y = y;
  }

  public double getX() {
    return X;
  }

  public double getY() {
    return Y;
  }

  @Override
  public String toString() {
    return "(" + this.getX() + "," + this.getY() + ")";
  }

}
