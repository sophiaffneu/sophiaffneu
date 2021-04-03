/**
 * One of the class that represents Transformation. This class scales the shape.
 */
public class Scale extends AbstractTransformation {
  private ShapeProperty newShapeProperty;

  public Scale(IShape transShape, TimePeriod timePeriod, ShapeProperty newShapeProperty,
               ShapeProperty shapeProperty) {
    super(transShape, timePeriod);
    this.newShapeProperty = newShapeProperty;
    transShape.setShapeProperty(shapeProperty);
  }

  /**
   * Return the Shape after transformation.
   *
   * @return the shape after transformation
   */
  public IShape transform() {
      transShape.setShapeProperty(newShapeProperty);
      return transShape;
    }

  @Override
  public String toString() {
    String output = "Shape " + transShape.getName() + " scales from ";
    if (transShape.getClass().toString().equals("class Rectangle")) {
      output += "Width: " + transShape.getShapeProperty().getOne()
          + ", Height: " + transShape.getShapeProperty().getTwo()
          + " to Width: " + newShapeProperty.getOne()
          + ", Height: " + newShapeProperty.getTwo();
    } else {
      output += "X radius: " + transShape.getShapeProperty().getOne()
          + ", Y radius: " + transShape.getShapeProperty().getTwo()
          + " to X radius: " + newShapeProperty.getOne()
          + ", Y radius: " + newShapeProperty.getTwo();
    }

    output += " from t=" + timePeriod.getStart() + " to t=" + timePeriod.getEnd();
    return output;
  }
}
