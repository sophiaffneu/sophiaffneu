package cs5004.animator.model;

import java.awt.*;

/**
 * One of the class that represents Transformation. This class changes the color of the shape.
 */
public class ChangeColor extends AbstractTransformation {

  private Point2D toPosition;
  private ShapeProperty toShapeProperty;
  private Color toColor;

  /**
   * Construct the change color transformation.
   *
   * @param transShape the shape gonna be changed
   * @param timePeriod changing period
   * @param toColor    new color
   */
  public ChangeColor(IShape transShape, TimePeriod timePeriod, Color toColor) {
    super(transShape, timePeriod);

    this.toColor = toColor;

    this.toShapeProperty = transShape.getShapeProperty();

    this.toPosition = transShape.getPosition();
    if (toColor == null || (toColor.getRed() == fromColor.getRed()
            && toColor.getBlue() == fromColor.getBlue()
            && toColor.getGreen() == fromColor.getGreen())) {
      throw new IllegalArgumentException("Invalid color parameter.");
    }
    transShape.setColor(toColor);
  }

  @Override
  public String toString() {
    String output = transShape.getName() + " changes from (" + fromColor.getRed() + "," + fromColor.getGreen() + "," + fromColor.getBlue() + ") "
            + " to (" + toColor.getRed() + "," + toColor.getGreen() + "," + toColor.getBlue() + ")" + " from t=" + timePeriod.getStart() + " to t=" + timePeriod.getEnd();
    return output;
  }

  @Override
  public TransType getTransType() {
    return TransType.CHANGECOLOR;
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
