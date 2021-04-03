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
  public ArrayList<IShape> getShapeList();

  /**
   * Add a list of transformation to of the shape to the map of transformations.
   *
   * @param list the transformation list to be added.
   */


  public void addTransformations(IShape s, List<ITransformation> list);

  /**
   * Remove a shape's transformation from the map of transformations.
   *
   * @param s, the shape whose transformations are to be removed.
   */

  public void removeTransformations(IShape s);

  /**
   * Get the Hashmap of shapes and transformations.
   *
   * @return the Hashmap of shapes and transformations.
   */
  public HashMap<IShape, List<ITransformation>> getTransMap ();

  /**
   * Get the text description of the animation.
   *
   * @return a type String, the text description of the animation.
   */
  public String getTransInfo();
}
