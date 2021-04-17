package cs5004.animator.model;

import java.awt.*;

/**
 * An abstract shape class.
 */
public abstract class AbstractShape implements IShape {
  protected String name;
  protected Point2D position;
  protected Color color;
  protected ShapeProperty shapeProperty;
  protected TimePeriod period;

  public AbstractShape(String name) {
    this.name = name;
  }
  /**
   * Construct the initial cs5004.animator.model.AbstractShape.
   *
   * @param name          the name of the shape.
   * @param position      the position of the shape.
   * @param color         the color of the shape.
   * @param shapeProperty the shapeProperties of the shape.
   */
  public AbstractShape(String name, Point2D position, Color color, ShapeProperty shapeProperty) {
    //The position of the shape has to be within the canvas. Will check if input is valid when the
    //canvas size info. is given in the coming assignments.
    /**
    if (name == null || position == null || color == null || shapeProperty == null) {
      throw new IllegalArgumentException("Parameters can not be null.");
    }*/

    this.name = name;
    this.position = position;
    this.color = color;
    this.shapeProperty = shapeProperty;
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
  public Color getColor() {
    return color;
  }

  @Override
  public void setColor(Color color) {
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

}
