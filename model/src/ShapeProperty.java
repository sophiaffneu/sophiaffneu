/**
 * This class represents the dimensions of the shape. Our current shapes are two dimensional. For
 * rectangles, one is width and two is height. For ovals , one is the x radius and two is the y
 * radius.
 */

public class ShapeProperty {
  private double one;
  private double two;

  /**
   * Construct the initial shape property.
   *
   * @param one width or x radius
   * @param two height or y radius
   */
  public ShapeProperty(double one, double two) {
    if (one <= 0 || two <= 0) {
      throw new IllegalArgumentException("ShapeProperties can not be none positive value");
    }
    this.one = one;
    this.two = two;
  }

  /**
   * Return the first one, width or x radius.
   *
   * @return the first one.
   */
  public double getOne() {
    return one;
  }

  /**
   * Return the second one, height or y radius.
   *
   * @return the second one.
   */
  public double getTwo() {
    return two;
  }

}
