public class ShapeProperty {
  private double One;
  private double Two;

  public ShapeProperty(double one, double two) {
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

  public void setOne(double one) {
    One = one;
  }

  public void setTwo(double two) {
    Two = two;
  }
}
