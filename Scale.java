/**
 * One of the class that represents Transformation. This class scales the shape.
 */
public class Scale extends AbstractTransformation {
  private ShapeProperty fromShapeProperty;

  public Scale(IShape transShape, TimePeriod timePeriod, ShapeProperty newShapeProperty) {
    super(transShape, timePeriod);
    this.fromShapeProperty = transShape.getShapeProperty();
    transShape.setShapeProperty(newShapeProperty);
  }


  @Override
  public String toString() {
    String output = "Shape " + transShape.getName() + " scales from ";
    if (transShape.getShapeType() == ShapeType.RECTANGLE) {
      output += "Width: " + fromShapeProperty.getOne()
          + ", Height: " + fromShapeProperty.getTwo()
          + " to Width: " + transShape.getShapeProperty().getOne()
          + ", Height: " + transShape.getShapeProperty().getTwo();
    } else {
      output += "X radius: " + fromShapeProperty.getOne()
          + ", Y radius: " + fromShapeProperty.getTwo()
          + " to X radius: " + transShape.getShapeProperty().getOne()
          + ", Y radius: " + transShape.getShapeProperty().getTwo();
    }

    output += " from t=" + timePeriod.getStart() + " to t=" + timePeriod.getEnd();
    return output;
  }
}
