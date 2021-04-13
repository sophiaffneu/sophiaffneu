package cs5004.animator.model;

import java.awt.*;

/**
 * An abstract transformation class.
 */
public abstract class AbstractTransformation implements ITransformation {

  protected IShape transShape;
  protected TimePeriod timePeriod;
  protected Point2D fromPosition;
  protected ShapeProperty fromShapeProperty;
 protected Color fromColor;


  /**
   * Construct the initial cs5004.animator.model.AbstractTransformation.
   *
   * @param transShape the shape that is to be changed.
   * @param timePeriod the time interval of the transformation.
   */
  public AbstractTransformation(IShape transShape, TimePeriod timePeriod) {
    if (transShape == null || timePeriod == null) {
      throw new IllegalArgumentException("Parameters can not be null.");
    }
    /*if (timePeriod.getStart() < transShape.getPeriod().getStart()
        || timePeriod.getEnd() > transShape.getPeriod().getEnd()) {
      throw new IllegalArgumentException("The transformation can only happen when the shape is "
          + "present.");
    }*/
    this.transShape = transShape;
    this.timePeriod = timePeriod;
    this.fromPosition = transShape.getPosition();
    this.fromColor = transShape.getColor();
    this.fromShapeProperty = transShape.getShapeProperty();
  }


  @Override
  public IShape getTransShape() {
    return transShape;
  }

  @Override
  public TimePeriod getTimePeriod() {
    return timePeriod;
  }

  @Override
  public Point2D getPosition() {
    return this.fromPosition;
  }

  @Override
  public ShapeProperty getShapeProperty() {
    return this.fromShapeProperty;
  }

  @Override
  public Color getColor() {
    return this.fromColor;
  }

}
