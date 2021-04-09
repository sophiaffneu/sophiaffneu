package cs5004.animator.model;

/**
 * An abstract transformation class.
 */
public abstract class AbstractTransformation implements ITransformation {

  protected IShape transShape;
  protected TimePeriod timePeriod;

  /**
   * Construct the initial cs5004.animator.model.AbstractTransformation.
   *
   * @param transShape the shape that is to be changed.
   *
   * @param timePeriod the time interval of the transformation.
   */
  public AbstractTransformation(IShape transShape, TimePeriod timePeriod) {
    if (transShape == null || timePeriod == null) {
      throw new IllegalArgumentException("Parameters can not be null.");
    }
    if (timePeriod.getStart() < transShape.getPeriod().getStart()
        || timePeriod.getEnd() > transShape.getPeriod().getEnd()) {
      throw new IllegalArgumentException("The transformation can only happen when the shape is "
          + "present.");
    }
    this.transShape = transShape;
    this.timePeriod = timePeriod;
  }


  @Override
  public IShape getTransShape() {
    return transShape;
  }

  @Override
  public TimePeriod getTimePeriod() {
    return timePeriod;
  }

}
