import java.util.ArrayList;
import java.util.List;

/**
 * An abstract shape class.
 */
public abstract class AbstractShape implements IShape {
  protected String name;
  protected Point2D position;
  protected ColorRGB color;
  protected ShapeProperty shapeProperty;
  protected TimePeriod period;

  /**
   * Construct the initial AbstractShape.
   *
   * @param name          the name of the shape.
   * @param position      the position of the shape.
   * @param color         the color of the shape.
   * @param shapeProperty the shapeProperties of the shape.
   * @param period        the showing and disappearing time range of the shape.
   */
  public AbstractShape(String name, Point2D position, ColorRGB color, ShapeProperty shapeProperty,
                       TimePeriod period) {
    //The position of the shape has to be within the canvas. Will check if input is valid when the
    //canvas size info. is given in the coming assignments.
    if (name == null || position == null || color == null || shapeProperty == null
        || period == null) {
      throw new IllegalArgumentException("Parameters can not be null.");
    }

    this.name = name;
    this.position = position;
    this.color = color;
    this.shapeProperty = shapeProperty;
    this.period = period;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public Point2D getPosition() {
    return position;
  }

  @Override
  public void setPosition(Point2D point2D) {
    if (point2D == null) {
      throw new IllegalArgumentException("Shape position can't be null.");
    }
    this.position = point2D;
  }


  @Override
  public ColorRGB getColor() {
    return color;
  }

  @Override
  public void setColor(ColorRGB color) {
    if (color == null) {
      throw new IllegalArgumentException("Shape color can't be null.");
    }
    this.color = color;
  }


  @Override
  public ShapeProperty getShapeProperty() {
    return this.shapeProperty;
  }

  @Override
  public void setShapeProperty(ShapeProperty shapeProperty) {
    if (shapeProperty == null) {
      throw new IllegalArgumentException("Shape properties can't be null.");
    }
    this.shapeProperty = shapeProperty;
  }


  @Override
  public TimePeriod getPeriod() {
    return period;
  }

  @Override
  public void setPeriod(TimePeriod t) {
    this.period = t;
  }
}
