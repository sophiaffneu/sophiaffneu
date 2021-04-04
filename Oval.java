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
    String output = "Name: " + this.getName() + "\n"
        + "Type: " + this.getShapeType() + "\n"
        + "Center: " + this.getPosition().toString()
        + ", X radius: " + this.getShapeProperty().getOne()
        + ", Y radius: " + this.getShapeProperty().getTwo()
        + ", Color: " + this.getColor().toString() + "\n"
        + "Appears at t=" + this.getPeriod().getStart() + "\n"
        + "Disappears at t=" + this.getPeriod().getEnd() + "\n";
    return output;
  }
}
