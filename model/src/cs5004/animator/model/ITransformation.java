package cs5004.animator.model;

import java.awt.*;

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

  /**
   * Get the position of the shape before transformation.
   *
   * @return Point2D the position of the shape before transformation.
   */

  Point2D getPosition();

  /**
   * Get the dimentions of the shape before transformation.
   *
   * @return ShapeProperty the dimentions of the shape before transformation.
   */

  ShapeProperty getShapeProperty();
  /**
   * Get the color of the shape before transformation.
   *
   * @return color the color of the shape before transformation.
   */
  Color getColor();

  /**
   * Get the position of the shape after transformation.
   *
   * @return Point2D the position of the shape after transformation.
   */
  Point2D getToPosition();

  /**
   * Get the dimentions of the shape after transformation.
   *
   * @return ShapeProperty the dimentions of the shape after transformation.
   */

  ShapeProperty getToShapeProperty();
  /**
   * Get the color of the shape after transformation.
   *
   * @return color the color of the shape after transformation.
   */
  Color getToColor();

}
