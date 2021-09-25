package cs5004.animator.model;

import java.awt.Color;

/**
 * One of the class that represents Transformation. This class moves the shape.
 */
public class Move extends AbstractTransformation {

  private Point2D toPosition;
  private ShapeProperty toShapeProperty;
  private Color toColor;

  /**
   * Construct the move transformation.
   *
   * @param transShape the shape gonna be changed
   * @param timePeriod change period
   * @param toPosition new position
   */
  public Move(IShape transShape, TimePeriod timePeriod, Point2D toPosition) {
    super(transShape, timePeriod);

    this.toPosition = toPosition;

    this.toColor = transShape.getColor();

    this.toShapeProperty = transShape.getShapeProperty();
    //The the whole shape has to be within the canvas after the move.
    // Will check if input is valid when the
    //canvas size info. is given in the coming assignments.
    if (toPosition == null || (toPosition.getX() == this.getPosition().getX()
        && toPosition.getY() == this.getPosition().getY())) {
      throw new IllegalArgumentException("Invalid toPosition parameter.");
    }
    transShape.setPosition(toPosition);
  }

  @Override
  public String toString() {
    String output = transShape.getName() + " moves from " + this.getPosition()
        + " to " + toPosition + " from t=" + timePeriod.getStart()
        + " to t=" + timePeriod.getEnd();
    return output;
  }

  @Override
  public TransType getTransType() {
    return TransType.MOVE;
  }

  @Override
  public Point2D getToPosition() {
    return this.toPosition;
  }

  @Override
  public ShapeProperty getToShapeProperty() {
    return this.toShapeProperty;
  }

  @Override
  public Color getToColor() {
    return this.toColor;
  }


}
