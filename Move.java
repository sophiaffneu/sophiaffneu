/**
 * One of the class that represents Transformation. This class moves the shape.
 */
public class Move extends AbstractTransformation {
  private Point2D fromPosition;

  public Move(IShape transShape, TimePeriod timePeriod, Point2D toPosition) {
    super(transShape, timePeriod);
    this.fromPosition = transShape.getPosition();
    transShape.setPosition(toPosition);
  }

  @Override
  public String toString() {
    String output = "Shape " + transShape.getName() + " moves from " + fromPosition
        + " to " + transShape.getPosition() + " from t=" + timePeriod.getStart() + " to t=" + timePeriod.getEnd();
    return output;
  }
}
