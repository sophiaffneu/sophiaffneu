package cs5004.animator.model;

/**
 * One of the class that represents Transformation. This class scales the shape.
 */
public class Scale extends AbstractTransformation {
  private ShapeProperty fromShapeProperty;
  private ShapeProperty toShapeProperty;

  /**
   * Construct the scale transformation.
   *
   * @param transShape       the shape gonna be changed
   * @param timePeriod       the period of the transformation
   * @param newShapeProperty the new shapePeoperty
   */
  public Scale(IShape transShape, TimePeriod timePeriod, ShapeProperty newShapeProperty) {
    super(transShape, timePeriod);
    this.fromShapeProperty = transShape.getShapeProperty();
    this.toShapeProperty = newShapeProperty;
    //The whole shape has to be within the canvas after the transformation. Will check if input
    // is valid when the canvas size info. is given in the coming assignments.
    if (newShapeProperty == null || (fromShapeProperty.getOne() == toShapeProperty.getOne()
        && fromShapeProperty.getTwo() == toShapeProperty.getTwo())) {
      throw new IllegalArgumentException("Invalid shaper property parameter.");
    }
    transShape.setShapeProperty(newShapeProperty);
  }


  @Override
  public String toString() {
    String output = transShape.getName();
    if (transShape.getShapeType() == ShapeType.RECTANGLE) {
      if(fromShapeProperty.getOne()!=toShapeProperty.getOne()){
        output+= " changes width from " + fromShapeProperty.getOne()
            + " to " + toShapeProperty.getOne();
      }
      if(fromShapeProperty.getTwo()!= toShapeProperty.getTwo()){
        output += " changes height from " + fromShapeProperty.getTwo()
            + " to " + toShapeProperty.getTwo();
      }
    } else {
      if(fromShapeProperty.getOne()!=toShapeProperty.getOne()){
        output+= " changes X radius from " + fromShapeProperty.getOne()
            + " to " + toShapeProperty.getOne();
      }
      if(fromShapeProperty.getTwo()!= toShapeProperty.getTwo()){
        output += " changes Y radius from " + fromShapeProperty.getTwo()
            + " to " + toShapeProperty.getTwo();
      }
    }

    output += " from t=" + timePeriod.getStart() + " to t=" + timePeriod.getEnd();
    return output;
  }

  @Override
  public TransType getTransType() {
    return TransType.SCALE;
  }
}
