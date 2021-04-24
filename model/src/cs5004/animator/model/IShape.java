package cs5004.animator.model;

import java.awt.Color;

/**
 * The interface represent all the functionalities a shape should have.
 */
public interface IShape {
  /**
   * Return the name of the Shape.
   *
   * @return the name of the shape
   */
  String getName();

  /**
   * Return the position of the shape.
   *
   * @return the position of the shape
   */
  Point2D getPosition();

  /**
   * Set the position of the shape.
   *
   * @param point2D the new position of the shape
   */
  void setPosition(Point2D point2D);

  /**
   * Return the color of the shape.
   *
   * @return the color of the shape
   */
  Color getColor();

  /**
   * Set the color of the shape.
   *
   * @param color the new color of the shape
   */
  void setColor(Color color);

  /**
   * Return the shape properties of the shape.
   *
   * @return the  shape properties of the shape
   */
  ShapeProperty getShapeProperty();

  /**
   * Set the shape properties of the shape.
   *
   * @param shapeProperty the new shape properties of the shape
   */
  void setShapeProperty(ShapeProperty shapeProperty);


  /**
   * Return the type of a shape.
   *
   * @return the type of a shape
   */

  ShapeType getShapeType();

  /**
   * Return a copy of the shape.
   *
   * @return a copy of the shape
   */

  IShape copyShape();

}
