/**
 * One of the class that represents a shape. This class represents rectangle.
 */
public class Rectangle extends AbstractShape {

  /**
   * Construct the initial rectangle.
   *
   * @param name          the name of the shape
   * @param position      the position of the position
   * @param color         the color of the shape
   * @param shapeProperty the shapeProperty of the shape
   * @param period        the period of the shape
   */
  public Rectangle(String name, Point2D position, ColorRGB color, ShapeProperty shapeProperty,
                   TimePeriod period) {
    super(name, position, color, shapeProperty, period);
  }

  @Override
  public IShape copyShape() {
    IShape newR = new Rectangle(this.getName(), this.getPosition(), this.getColor(),
        this.shapeProperty, this.period);
    return newR;
  }


  public ShapeType getShapeType() {
    return ShapeType.RECTANGLE;
  }

  @Override
  public String toString() {
    String output = "Name: " + this.getName() + "\n"
        + "Type: " + this.getShapeType() + "\n"
        + "Min corner: " + this.getPosition().toString()
        + ", Width: " + this.shapeProperty.getOne() + ", Height: " + this.shapeProperty.getTwo()
        + ", Color: " + this.getColor().toString() + "\n"
        + "Appears at t=" + this.getPeriod().getStart() + "\n"
        + "Disappears at t=" + this.getPeriod().getEnd() + "\n";
    return output;
  }
}
