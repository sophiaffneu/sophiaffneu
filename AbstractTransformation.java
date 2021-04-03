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
    if (transShape == null || timePeriod == null) {
      throw new IllegalArgumentException("Parameters can not be null.");
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
