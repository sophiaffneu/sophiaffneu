import java.util.ArrayList;
import java.util.List;

/**
 * The interface of Shape.
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
   * @param point2D, the new position of the shape.
   */
  public void setPosition(Point2D point2D);

  /**
   * Return the color of the shape.
   *
   * @return the color of the shape
   */
  ColorRGB getColor();

  /**
   * Set the color of the shape.
   *
   * @param color, the new color of the shape.
   */
  public void setColor(ColorRGB color);

  /**
   * Return the shape properties of the shape.
   *
   * @return the  shape properties of the shape.
   */
  public ShapeProperty getShapeProperty();

  /**
   * Set the shape properties of the shape.
   *
   * @param shapeProperty, the new shape properties of the shape.
   */
  public void setShapeProperty(ShapeProperty shapeProperty);

  TimePeriod getPeriod();

  /**
   * make a copy of the shape
   *
   * @return a copy of the shape.
   */
  public IShape copyShape();

  /**
   * Add a transformation to the list of transformations.
   *
   * @param t the transformation to be added.
   */


  public void addTransformations(ITransformation t);

  /**
   * Remove a transformation from the list of transformations.
   *
   * @param t the transformation to be removed.
   */

  public void removeTransformations(ITransformation t);

  /**
   * Return the transformation list.
   *
   * @return the transformation list.
   */
  public List<ITransformation> getTransformationList();

  public void setTransformationList(List<ITransformation> transformationList);
}
