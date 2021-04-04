/**
 * This class represents the size of the shape.
 */
public class ShapeProperty {
  private double One;
  private double Two;

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
    this.One = one;
    this.Two = two;
  }

  /**
   * Return the first one, width or x radius.
   *
   * @return the first one.
   */
  public double getOne() {
    return One;
  }

  /**
   * Return the second one, height or y radius.
   *
   * @return the second one.
   */
  public double getTwo() {
    return Two;
  }

}