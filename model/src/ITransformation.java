/**
 * The interface for transformation.
 */
public interface ITransformation {


  /**
   * Get the shape to be changed.
   *
   * @return the shape to be changed
   */
  IShape getTransShape();


  /**
   * Return the time range of transformation.
   *
   * @return the time range of transformation
   */
  TimePeriod getTimePeriod();

  /**
   * Get the type of a transformation.
   *
   * @return the transformed type
   */
  TransType getTransType();

}
