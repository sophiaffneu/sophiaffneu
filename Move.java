/**
 * One of the class that represents Transformation. This class moves the shape.
 */
public class Move extends AbstractTransformation {
  private Point2D fromPosition;
  private Point2D toPosition;

  public Move(IShape transShape, TimePeriod timePeriod, Point2D toPosition) {
    super(transShape, timePeriod);
    this.fromPosition = transShape.getPosition();
    this.toPosition = toPosition;
    if(toPosition == null || (toPosition.getX() == fromPosition.getX() &&
            toPosition.getY() == fromPosition.getY())) {
      throw new IllegalArgumentException("Invalid toPosition parameter.");
    }
    transShape.setPosition(toPosition);
  }

  @Override
  public String toString() {
    String output = "Shape " + transShape.getName() + " moves from " + fromPosition
        + " to " + toPosition + " from t=" + timePeriod.getStart() + " to t=" + timePeriod.getEnd();
    return output;
  }

  @Override
  public TransType getTransType() {
    return TransType.MOVE;
  }
}
