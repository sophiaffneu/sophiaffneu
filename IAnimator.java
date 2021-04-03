import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Interface for animation model.
 */
public interface IAnimator {
  /**
   * Add a shape.
   *
   * @param s shape
   */
  public void addShape(IShape s);

  /**
   * Remove a shape from list.
   *
   * @param shape to be removed.
   */

  public void removeShape(IShape shape);

  /**
   * Return the shape list.
   *
   * @return the shape list
   */
  public List<IShape> getShapeList();

  /**
   * Add a transformation to the list of transformations.
   *
   * @param t the transformation to be added.
   */


  public void addTransformations(ITransformation t);

  /**
   * Remove a shape's transformation from the map of transformations.
   *
   * @param s, the shape whose transformations are to be removed.
   */

  public void removeTransformations(ITransformation t);

  /**
   * Get the Hashmap of shapes and transformations.
   *
   * @return the Hashmap of shapes and transformations.
   */
  public List<ITransformation> getTransList();


  /**
   * Get the text description of the animation.
   *
   * @return a type String, the text description of the animation.
   */
  public String getTransInfo();

  /**
   * Get the text description of a shape.
   *
   * @return a type String, the text description of a shape.
   */
  public String getShapesInfo();
}
