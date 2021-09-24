import org.junit.Before;
import org.junit.Test;


import java.awt.Color;

import static org.junit.Assert.assertEquals;

import cs5004.animator.model.IShape;
import cs5004.animator.model.Oval;
import cs5004.animator.model.Point2D;
import cs5004.animator.model.Rectangle;
import cs5004.animator.model.ShapeProperty;
import cs5004.animator.model.ShapeType;

/**
 * JUnit test for shape.
 */
public class ShapeTest {

  private IShape r1;
  private IShape o1;

  @Before
  public void setup() {
    r1 = new Rectangle("R", new Point2D(200, 200), new Color(1, 0, 0),
        new ShapeProperty(50, 100));

    o1 = new Oval("C", new Point2D(500, 100), new Color(0, 0, 1),
        new ShapeProperty(60, 30));
  }

  @Test
  public void testToString() {
    assertEquals("Create (1,0,0) rectangle R with corner at (200,200"
        + "), width 50 and height 100", r1.toString());

    assertEquals("Create (0,0,1) oval C with center at (500,100"
        + "), radius 60 and 30", o1.toString());
  }

  @Test
  public void testGetName() {
    assertEquals("R", r1.getName());
    assertEquals("C", o1.getName());
  }

  @Test
  public void testPosition() {
    assertEquals(200, r1.getPosition().getX(), 0.01);
    assertEquals(200, r1.getPosition().getY(), 0.01);
    r1.setPosition(new Point2D(500, 600));
    assertEquals(500, r1.getPosition().getX(), 0.01);
    assertEquals(600, r1.getPosition().getY(), 0.01);
  }

  @Test
  public void testColor() {
    assertEquals(1.0, r1.getColor().getRed(), 0.01);
    assertEquals(0, r1.getColor().getGreen(), 0.01);
    assertEquals(0, r1.getColor().getBlue(), 0.01);
    r1.setColor(new Color(0, 1, 1));
    assertEquals(0, r1.getColor().getRed(), 0.01);
    assertEquals(1, r1.getColor().getGreen(), 0.01);
    assertEquals(1, r1.getColor().getBlue(), 0.01);
  }

  @Test
  public void testShapeProperty() {
    assertEquals(50, r1.getShapeProperty().getOne());
    assertEquals(100, r1.getShapeProperty().getTwo());
    r1.setShapeProperty(new ShapeProperty(200, 300));
    assertEquals(200, r1.getShapeProperty().getOne());
    assertEquals(300, r1.getShapeProperty().getTwo());
  }

  @Test
  public void testGetShapeType() {
    assertEquals(ShapeType.RECTANGLE, r1.getShapeType());
    assertEquals(ShapeType.OVAL, o1.getShapeType());
  }

  @Test
  public void testCopyShape() {
    IShape copy = r1.copyShape();
    assertEquals(copy.getName(), r1.getName());
    assertEquals(copy.getPosition().getX(), r1.getPosition().getX(), 0.01);
    assertEquals(copy.getPosition().getY(), r1.getPosition().getY(), 0.01);
    assertEquals(copy.getColor().getRed(), r1.getColor().getRed(), 0.01);
    assertEquals(copy.getColor().getGreen(), r1.getColor().getGreen(), 0.01);
    assertEquals(copy.getColor().getBlue(), r1.getColor().getBlue(), 0.01);
    assertEquals(copy.getShapeType(), r1.getShapeType());
  }
}
