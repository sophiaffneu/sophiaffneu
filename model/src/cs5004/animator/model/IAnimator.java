package cs5004.animator.model;

import java.util.List;

import cs5004.animator.util.AnimationBuilder;

/**
 * Interface for animation model.
 */
public interface IAnimator {
  /**
   * Specify the bounding box to be used for the animation.
   * @param x The leftmost x value
   * @param y The topmost y value
   * @param width The width of the bounding box
   * @param height The height of the bounding box
   * @return This {@link AnimationBuilder}
   */
  IAnimator setBounds(int x, int y, int width, int height);

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


  List<IShape> getShapeAtTick(int t);
}
