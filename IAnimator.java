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
  void addShape(IShape s);

  /**
   * Remove a shape from list.
   *
   * @param shape to be removed
   */

  void removeShape(IShape shape);

  /**
   * Return the shape list.
   *
   * @return the shape list
   */
  List<IShape> getShapeList();

  /**
   * Add a transformation to the list of transformations.
   *
   * @param t the transformation to be added
   */


  void addTransformations(ITransformation t);

  /**
   * Remove a shape's transformation from the map of transformations.
   *
   * @param t the transformation
   */

  void removeTransformations(ITransformation t);

  /**
   * Get the Hashmap of shapes and transformations.
   *
   * @return the Hashmap of shapes and transformations
   */
  List<ITransformation> getTransList();


  /**
   * Get the text description of the animation.
   *
   * @return a type String, the text description of the animation
   */
  String getTransInfo();

  /**
   * Get the text description of a shape.
   *
   * @return a type String, the text description of a shape
   */

  String getShapesInfo();

  // List<IShape> getShapeAtTick();
}
