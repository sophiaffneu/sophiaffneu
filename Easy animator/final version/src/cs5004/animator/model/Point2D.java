package cs5004.animator.model;

/**
 * This class represents a position of a point.
 */
public class Point2D {
  private int x;
  private int y;

  /**
   * Construct the initial cs5004.animator.model.Point2D.
   *
   * @param x x of the position
   * @param y y of the position
   */
  public Point2D(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  @Override
  public String toString() {
    return "(" + this.getX() + "," + this.getY() + ")";
  }

}
