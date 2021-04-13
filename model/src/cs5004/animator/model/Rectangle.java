package cs5004.animator.model;

import java.awt.*;

/**
 * One of the class that represents a shape. This class represents rectangle.
 */
public class Rectangle extends AbstractShape {
  /**
   * Construct the initial cs5004.animator.model.AbstractShape.
   *
   * @param name the name of the shape.
   */

  public Rectangle(String name) {
    super(name);
  }

  /**
   * Construct the initial rectangle.
   *
   * @param name          the name of the shape
   * @param position      the position of the position
   * @param color         the color of the shape
   * @param shapeProperty the shapeProperty of the shape.
   */

  public Rectangle(String name, Point2D position, Color color, ShapeProperty shapeProperty) {
    super(name, position, color, shapeProperty);
  }

  @Override
  public IShape copyShape() {
    IShape newR = new Rectangle(this.getName(), this.getPosition(), this.getColor(),
            this.shapeProperty);
    return newR;
  }


  public ShapeType getShapeType() {
    return ShapeType.RECTANGLE;
  }

  @Override
  public String toString() {
    String output = "Create (" + this.getColor().getRed() + ","
            + this.getColor().getGreen() + ","
            + this.getColor().getBlue() + ") "
            + this.getShapeType() + " " + this.getName()
            + " with corner at "
            + this.getPosition().toString()
            + ", width " + this.getShapeProperty().getOne()
            + " and height " + this.getShapeProperty().getTwo();
    return output;
  }
}