package cs5004.animator.model;

public class NoChange extends AbstractTransformation{
  /**
   * Construct the initial cs5004.animator.model.AbstractTransformation.
   *
   * @param transShape the shape that is to be changed.
   * @param timePeriod the time interval of the transformation.
   */
  public NoChange(IShape transShape, TimePeriod timePeriod) {
    super(transShape, timePeriod);
  }

  @Override
  public TransType getTransType() {
    return TransType.NOCHANGE;
  }
}
