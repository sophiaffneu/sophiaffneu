/**
 * One of the class that represents Transformation. This class scales the shape.
 */
public class Scale extends AbstractTransformation {
  private ShapeProperty fromShapeProperty;
  private ShapeProperty toShapeProperty;

  public Scale(IShape transShape, TimePeriod timePeriod, ShapeProperty newShapeProperty) {
    super(transShape, timePeriod);
    this.fromShapeProperty = transShape.getShapeProperty();
    this.toShapeProperty = newShapeProperty;
    if(newShapeProperty == null || (fromShapeProperty.getOne() == toShapeProperty.getOne() &&
            fromShapeProperty.getTwo() == toShapeProperty.getTwo())) {
      throw new IllegalArgumentException("Invalid shaper property parameter.");
    }
    transShape.setShapeProperty(newShapeProperty);
  }


  @Override
  public String toString() {
    String output = "Shape " + transShape.getName() + " scales from ";
    if (transShape.getShapeType() == ShapeType.RECTANGLE) {
      output += "Width: " + fromShapeProperty.getOne()
          + ", Height: " + fromShapeProperty.getTwo()
          + " to Width: " + toShapeProperty.getOne()
          + ", Height: " + toShapeProperty.getTwo();
    } else {
      output += "X radius: " + fromShapeProperty.getOne()
          + ", Y radius: " + fromShapeProperty.getTwo()
          + " to X radius: " + toShapeProperty.getOne()
          + ", Y radius: " + toShapeProperty.getTwo();;
    }

    output += " from t=" + timePeriod.getStart() + " to t=" + timePeriod.getEnd();
    return output;
  }

  @Override
  public TransType getTransType() {
    return TransType.SCALE;
  }
}
