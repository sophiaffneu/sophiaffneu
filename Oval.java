/**
 * One of the class that represent shape. This class represents oval.
 */
public class Oval extends AbstractShape {
  
  public Oval(String name, Point2D position, ColorRGB color, ShapeProperty shapeProperty,
              TimePeriod period) {
    super(name, position, color, shapeProperty,period);
  }

  public ShapeType getShapeType(){
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
        + "Type: oval\n"
        + "Center: " + this.getPosition().toString()
        + ", X radius: " + this.getShapeProperty().getOne()
        + ", Y radius: " + this.getShapeProperty().getTwo()
        + ", Color: " + this.getColor().toString() + "\n"
        +"Appears at t=" + this.getPeriod().getStart() + "\n"
        +"Disappears at t=" + this.getPeriod().getEnd() + "\n";
    return output;
  }
}
