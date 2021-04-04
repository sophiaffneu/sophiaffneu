/**
 * This class represents rgb information.
 */
public class ColorRGB {
  private double r;
  private double g;
  private double b;

  /**
   * Construct the initial color.
   *
   * @param r r value of the color
   * @param g g value of the color
   * @param b b value of the color
   */
  public ColorRGB(double r, double g, double b) {
    if ((r >= 0 && r <= 1) && (g >= 0 && g <= 1) && (b >= 0 && b <= 1)) {
      this.r = r;
      this.g = g;
      this.b = b;
    } else {
      throw new IllegalArgumentException("invalid rgb");
    }
  }

  public double getR() {
    return r;
  }

  public double getG() {
    return g;
  }

  public double getB() {
    return b;
  }

  @Override
  public String toString() {
    return "(" + this.getR() + "," + this.getG() + "," + this.getB() + ")";
  }
}