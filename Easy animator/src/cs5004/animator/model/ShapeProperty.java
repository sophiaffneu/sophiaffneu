package cs5004.animator.model;

/**
 * This class represents the dimensions of the shape. Our current shapes are two dimensional. For
 * rectangles, one is width and two is height. For ovals , one is the x radius and two is the y
 * radius.
 */

public class ShapeProperty {
  private int one;
  private int two;

  /**
   * Construct the initial shape property.
   *
   * @param one width or x radius
   * @param two height or y radius
   */
  public ShapeProperty(int one, int two) {
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
  public int getOne() {
    return one;
  }

  /**
   * Return the second one, height or y radius.
   *
   * @return the second one.
   */
  public int getTwo() {
    return two;
  }

}