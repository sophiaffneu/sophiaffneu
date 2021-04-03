/**
 * An abstract transformation class.
 */
public abstract class AbstractTransformation implements ITransformation {

  protected IShape transShape;
  protected TimePeriod timePeriod;

  /**
   * Construct the initial AbstractTransformation.
   *
   * @param transShape the shape that gonna be changed
   */
  public AbstractTransformation(IShape transShape, TimePeriod timePeriod) {
    this.transShape = transShape;
    this.timePeriod = timePeriod;
  }
  
 /**
 * Get the shape after it is transformed.
 * @return the transformed shape.
 */
   @Override
  public IShape getTransShape() {
    return transShape;
  }
  
   @Override
  public TimePeriod getTimePeriod() {
    return timePeriod;
  }

}
