package cs5004.animator.model;

/**
 * One of the class that represent shape. This class represents oval.
 */
public class Oval extends AbstractShape {

  /**
   * Construct the initial oval.
   *
   * @param name          the name of the shape
   * @param position      the position of the shape
   * @param color         the color of the shape
   * @param shapeProperty the shapeProperty of the shape
   * @param period        the time period of the shape
   */
  public Oval(String name, Point2D position, ColorRGB color, ShapeProperty shapeProperty,
              TimePeriod period) {
    super(name, position, color, shapeProperty, period);
  }

  @Override
  public ShapeType getShapeType() {
    return ShapeType.OVAL;
  }

  @Override
  public IShape copyShape() {
    IShape newOval = new Oval(this.getName(), this.getPosition(), this.getColor(),
        this.shapeProperty, this.period);
    return newOval;
  }

  @Override
  public String toString() {
    String output = "Create " + this.getColor() + " " + this.getShapeType() + " "
        + this.getName() + " with center at "
        + this.getPosition().toString()
        + ", radius " + this.getShapeProperty().getOne()
        + " and " + this.getShapeProperty().getTwo();
    return output;
  }
}
