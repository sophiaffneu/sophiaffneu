/**
 * One of the class that represents Transformation. This class moves the shape.
 */
public class Move extends AbstractTransformation {
  private Point2D fromPosition;
  private Point2D toPosition;

  /**
   * Construct the move transformation.
   *
   * @param transShape the shape gonna be changed
   * @param timePeriod change period
   * @param toPosition new position
   */
  public Move(IShape transShape, TimePeriod timePeriod, Point2D toPosition) {
    super(transShape, timePeriod);
    this.fromPosition = transShape.getPosition();
    this.toPosition = toPosition;
    //The the whole shape has to be within the canvas after the move.
    // Will check if input is valid when the
    //canvas size info. is given in the coming assignments.
    if (toPosition == null || (toPosition.getX() == fromPosition.getX()
        && toPosition.getY() == fromPosition.getY())) {
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
