/**
 * One of the class that represents Transformation. This class changes the color of the shape.
 */
public class ChangeColor extends AbstractTransformation {
  private ColorRGB fromColor;
  private ColorRGB toColor;

  public ChangeColor(IShape transShape, TimePeriod timePeriod, ColorRGB toColor) {
    super(transShape, timePeriod);
    this.fromColor = transShape.getColor();
    this.toColor = toColor;
    if(toColor == null || (toColor.getR() == fromColor.getR() && toColor.getB() == fromColor.getB()
            && toColor.getG() == fromColor.getG())) {
      throw new IllegalArgumentException("Invalid color parameter.");
    }
    transShape.setColor(toColor);
  }

  @Override
  public String toString() {
    String output = "Shape " + transShape.getName() + " changes color from " + fromColor
            + " to " + toColor + " from t=" + timePeriod.getStart() + " to t=" + timePeriod.getEnd();
    return output;
  }

  @Override
  public TransType getTransType() {
    return TransType.CHANGECOLOR;
  }
}
