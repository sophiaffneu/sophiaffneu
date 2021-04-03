/**
 * This class represents rgb information.
 */
public class ColorRGB {
  private double R;
  private double G;
  private double B;

  /**
   * Construct the initial color.
   *
   * @param r r value of the color
   * @param g g value of the color
   * @param b b value of the color
   */
  public ColorRGB(double r, double g, double b) {
    if ((r >= 0 && r <= 1) && (g >= 0 && g <= 1) && (b >= 0 && b <= 1)) {
      this.R = r;
      this.G = g;
      this.B = b;
    } else {
      throw new IllegalArgumentException("invalid rgb");
    }
  }

  public double getR() {
    return R;
  }

  public double getG() {
    return G;
  }

  public double getB() {
    return B;
  }

  @Override
  public String toString() {
    return "(" + this.getR() + "," + this.getG() + "," + this.getB() + ")";
  }
}
