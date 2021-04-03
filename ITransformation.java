/**
 * The interface for transformation.
 */
public interface ITransformation {

  /**
   * Return the shape after transformation.
   *
   * @return the shape after transformation.
   */
  IShape transform();
  
    
 /**
 * Get the shape after it is transformed.
 * @return the transformed shape.
 */
  public IShape getTransShape();

 
  /**
   * Return the time range of transformation.
   *
   * @return the time range of transformation.
   */
  public TimePeriod getTimePeriod();
}
