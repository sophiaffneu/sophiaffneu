package cs5004.animator.model;

/**
 * One of the class that represents Transformation. This class changes the color of the shape.
 */
public class ChangeColor extends AbstractTransformation {
  private ColorRGB fromColor;
  private ColorRGB toColor;

  /**
   * Construct the change color transformation.
   *
   * @param transShape the shape gonna be changed
   * @param timePeriod changing period
   * @param toColor    new color
   */
  public ChangeColor(IShape transShape, TimePeriod timePeriod, ColorRGB toColor) {
    super(transShape, timePeriod);
    this.fromColor = transShape.getColor();
    this.toColor = toColor;
    if (toColor == null || (toColor.getR() == fromColor.getR() && toColor.getB() == fromColor.getB()
        && toColor.getG() == fromColor.getG())) {
      throw new IllegalArgumentException("Invalid color parameter.");
    }
    transShape.setColor(toColor);
  }

  @Override
  public String toString() {
    String output = transShape.getName() + " changes from " + fromColor
        + " to " + toColor + " from t=" + timePeriod.getStart() + " to t=" + timePeriod.getEnd();
    return output;
  }

  @Override
  public TransType getTransType() {
    return TransType.CHANGECOLOR;
  }
}
