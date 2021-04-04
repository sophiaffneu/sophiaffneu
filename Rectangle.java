/**
 * One of the class that represents a shape. This class represents rectangle.
 */
public class Rectangle extends AbstractShape {

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


  public ShapeType getShapeType(){
    return ShapeType.RECTANGLE;
  }
  
  @Override
  public String toString() {
    String output = "Name: " + this.getName() + "\n"
        + "Type: rectangle\n"
        + "Min corner: " + this.getPosition().toString()
        + ", Width: " + this.shapeProperty.getOne() + ", Height: " + this.shapeProperty.getTwo()
        + ", Color: " + this.getColor().toString() + "\n"
        +"Appears at t=" + this.getPeriod().getStart() + "\n"
        +"Disappears at t=" + this.getPeriod().getEnd() + "\n";
    return output;
  }
}
