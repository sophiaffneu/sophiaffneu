/**
 * One of the class that represents Transformation. This class moves the shape.
 */
public class Move extends AbstractTransformation {
  private Point2D toPosition;

  public Move(IShape transShape, TimePeriod timePeriod, Point2D toPosition,
              Point2D fromPosition) {
    super(transShape, timePeriod);
    this.toPosition = toPosition;
    transShape.setPosition(fromPosition);
  }

  /**
   * Return the Shape after transformation.
   *
   * @return the shape after transformation
   */
  public IShape transform() {
    transShape.setPosition(toPosition);
    return transShape;
  }


  @Override
  public String toString() {
    String output = "Shape " + transShape.getName() + " moves from " + transShape.getPosition()
        + " to " + toPosition + " from t=" + timePeriod.getStart() + " to t=" + timePeriod.getEnd();
    return output;
  }
}
