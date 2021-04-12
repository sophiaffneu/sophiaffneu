import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import cs5004.animator.model.*;
import cs5004.animator.model.Rectangle;

/**
 * A JUnit test for changeColor class.
 */
public class ChangeColorTest {
  private ITransformation c1;
  private ITransformation c2;
  private ITransformation illegalC;
  private IShape r;

  @Before
  public void setup() {
    r = new Rectangle("R", new Point2D(200, 200),
        new Color(1, 0, 0), new ShapeProperty(50, 100));
    IShape c = new Oval("C", new Point2D(500, 100),
        new Color(0, 0, 1), new ShapeProperty(60, 30));
    c1 = new ChangeColor(r.copyShape(), new TimePeriod(10, 50),
        new Color(0, 1, 0));
    c2 = new ChangeColor(c.copyShape(), new TimePeriod(50, 80),
        new Color(1, 1, 0));
  }

  @Test
  public void testGetTransType() {
    assertEquals(TransType.CHANGECOLOR, c1.getTransType());
    assertEquals(TransType.CHANGECOLOR, c2.getTransType());
  }

  @Test
  public void getTimePeriod() {
    assertEquals(10, c1.getTimePeriod().getStart());
    assertEquals(50, c1.getTimePeriod().getEnd());
    assertEquals(50, c2.getTimePeriod().getStart());
    assertEquals(80, c2.getTimePeriod().getEnd());
  }

  @Test
  public void testGetTransShape() {
    assertEquals("R", c1.getTransShape().getName());
    assertEquals(200, c1.getTransShape().getPosition().getX());
    assertEquals(200, c1.getTransShape().getPosition().getY());

    assertEquals(0, c1.getTransShape().getColor().getRed(), 0.01);
    assertEquals(1, c1.getTransShape().getColor().getGreen(), 0.01);
    assertEquals(0, c1.getTransShape().getColor().getBlue(), 0.01);

    assertEquals(50, c1.getTransShape().getShapeProperty().getOne());
    assertEquals(100, c1.getTransShape().getShapeProperty().getTwo());

    assertEquals("C", c2.getTransShape().getName());
    assertEquals(500, c2.getTransShape().getPosition().getX());
    assertEquals(100, c2.getTransShape().getPosition().getY());

    assertEquals(1, c2.getTransShape().getColor().getRed(), 0.01);
    assertEquals(1, c2.getTransShape().getColor().getGreen(), 0.01);
    assertEquals(0, c2.getTransShape().getColor().getBlue(), 0.01);

    assertEquals(60, c2.getTransShape().getShapeProperty().getOne());
    assertEquals(30, c2.getTransShape().getShapeProperty().getTwo());


  }

  @Test
  public void testIllegalColor1() {
    try {
      IShape n = null;
      illegalC = new ChangeColor(n, new TimePeriod(10, 50), new Color(0, 1, 0));
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("Parameters can not be null.", e.getMessage());
    }
  }

  @Test
  public void testIllegalColor2() {
    try {
      TimePeriod t = null;
      illegalC = new ChangeColor(r.copyShape(), t, new Color(0, 1, 0));
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("Parameters can not be null.", e.getMessage());
    }
  }

  @Test
  public void testIllegalColor3() {
    try {
      Color p = null;
      illegalC = new ChangeColor(r.copyShape(), new TimePeriod(10, 50), p);
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid color parameter.", e.getMessage());
    }
  }

  @Test
  public void testIllegalColor4() {
    try {
      illegalC = new ChangeColor(r.copyShape(), new TimePeriod(10, 50),
          new Color(1, 0, 0));
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid color parameter.", e.getMessage());
    }
  }

  @Test
  public void testIllegalColor5() {
    try {
      illegalC = new ChangeColor(r.copyShape(), new TimePeriod(10, 50),
          new Color(1, 0, 0));
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid color parameter.", e.getMessage());
    }
  }

}

