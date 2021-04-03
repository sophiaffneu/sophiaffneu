/**
 * One of the class that represents Transformation. This class changes the color of the shape.
 */
public class ChangeColor extends AbstractTransformation {
  private ColorRGB toColor;

  public ChangeColor(TimePeriod timePeriod, IShape transShape, ColorRGB toColor,
                     ColorRGB fromColor) {
    super(transShape, timePeriod);
    this.toColor = toColor;
   transShape.setColor(toColor);
  }

  /**
   * Return the Shape after transformation.
   *
   * @return the shape after transformation
   */
  public IShape transform() {
        transShape.setColor(toColor);
        return transShape;
    }

  @Override
  public String toString() {
    String output = "Shape " + transShape.getName() + " changes color from " + transShape.getColor()
        + " to " + toColor + " from t=" + timePeriod.getStart() + " to t=" + timePeriod.getEnd();
    return output;
  }
}
