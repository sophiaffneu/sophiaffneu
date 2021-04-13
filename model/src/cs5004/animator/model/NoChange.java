package cs5004.animator.model;

import java.awt.*;

public class NoChange extends AbstractTransformation{

  private Point2D toPosition;
  private ShapeProperty toShapeProperty;
  private Color toColor;
  /**
   * Construct the initial cs5004.animator.model.AbstractTransformation.
   *
   * @param transShape the shape that is to be changed.
   * @param timePeriod the time interval of the transformation.
   */
  public NoChange(IShape transShape, TimePeriod timePeriod) {
    super(transShape, timePeriod);
    this.toPosition = transShape.getPosition();
    this.toShapeProperty = transShape.getShapeProperty();
    this.toColor = transShape.getColor();
  }

  @Override
  public TransType getTransType() {
    return TransType.NOCHANGE;
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

  @Override
  public String toString() {
    String output = transShape.getName() + " moves from " + this.fromPosition
            + " to " + this.toPosition + " change from " + this.fromShapeProperty.getOne()
            + " to " + this.toShapeProperty.getOne()+ " change from " + this.fromShapeProperty.getTwo()
            + " to " + this.toShapeProperty.getTwo() + " color from " + this.fromColor
            + " to " + this.fromColor +" from t=" + timePeriod.getStart() + " to t=" + timePeriod.getEnd();
    return output;
  }
}
