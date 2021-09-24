package cs5004.animator.model;


import java.util.List;


/**
 * Interface for animation model.
 */
public interface IAnimator {

  /**
   * Specify the bounding box to be used for the animation.
   *
   * @param x      The leftmost x value
   * @param y      The topmost y value
   * @param width  The width of the bounding box
   * @param height The height of the bounding box
   * @return the Animator with bounds set
   */
  IAnimator setBounds(int x, int y, int width, int height);

  /**
   * Return the x position of the animation.
   *
   * @return the x position of the animation
   */
  int getX();

  /**
   * Return the y position of the animation.
   *
   * @return the y position of the animation
   */
  int getY();

  /**
   * Return the width.
   *
   * @return the width
   */
  int getWidth();

  /**
   * Return the height.
   *
   * @return the height
   */
  int getHeight();

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
   * Return a list of shape.
   *
   * @param t     tick
   * @param speed speed
   * @return a list of shape
   */
  List<IShape> getShapeAtTick(int t, int speed);
}
