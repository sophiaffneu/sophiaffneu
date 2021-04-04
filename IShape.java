import java.util.ArrayList;
import java.util.List;

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

  /**
   * Return the change time period  of a transformation.
   *
   * @return the change time period  of a transformation.
   */
  public TimePeriod getPeriod();

  /**
   * Set the showing and disappearing time of the shape.
   *
   * @param t, the showing and disappearing time of the shape.
   */

  public void setPeriod(TimePeriod t);

  /**
   * Return the type of a shape.
   *
   * @return the type of a shape.
   */

  ShapeType getShapeType();

  /**
   * make a copy of the shape
   *
   * @return a copy of the shape.
   */

    public IShape copyShape();
}
