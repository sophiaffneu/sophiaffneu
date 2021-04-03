/**
 * One of the class that represents Transformation. This class changes the color of the shape.
 */
public class ChangeColor extends AbstractTransformation {
  private ColorRGB fromColor;

  public ChangeColor(IShape transShape, TimePeriod timePeriod, ColorRGB toColor) {
    super(transShape, timePeriod);
    this.fromColor = transShape.getColor();
    transShape.setColor(toColor);
  }


  @Override
  public String toString() {
    String output = "Shape " + transShape.getName() + " changes color from " + fromColor
            + " to " + transShape.getColor() + " from t=" + timePeriod.getStart() + " to t=" + timePeriod.getEnd();
    return output;
  }
}
