import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import cs5004.animator.model.*;
import cs5004.animator.model.Rectangle;

/**
 * A JUnit test for scale class.
 */
public class ScaleTest {
  private ITransformation s1;
  private ITransformation s2;
  private ITransformation illegalS;
  private IShape r;
  private IShape c;

  @Before
  public void setup() {
    r = new Rectangle("R", new Point2D(200, 200),
        new Color(1, 0, 0), new ShapeProperty(50, 100));
    c = new Oval("C", new Point2D(500, 100),
        new Color(0, 0, 1), new ShapeProperty(60, 30));
    s1 = new Scale(c.copyShape(), new TimePeriod(10, 50), new ShapeProperty(25, 10));
    s2 = new Scale(r.copyShape(), new TimePeriod(51, 70), new ShapeProperty(25, 100));
  }


  @Test
  public void testGetTransType() {
    assertEquals(TransType.SCALE, s1.getTransType());
    assertEquals(TransType.SCALE, s2.getTransType());
  }

  @Test
  public void getTimePeriod() {
    assertEquals(10, s1.getTimePeriod().getStart());
    assertEquals(50, s1.getTimePeriod().getEnd());
    assertEquals(51, s2.getTimePeriod().getStart());
    assertEquals(70, s2.getTimePeriod().getEnd());
  }

  @Test
  public void testGetTransShpe() {
    assertEquals("C", s1.getTransShape().getName());
    assertEquals(500, s1.getTransShape().getPosition().getX());
    assertEquals(100, s1.getTransShape().getPosition().getY());

    assertEquals(0, s1.getTransShape().getColor().getRed(), 0.01);
    assertEquals(0, s1.getTransShape().getColor().getGreen(), 0.01);
    assertEquals(1, s1.getTransShape().getColor().getBlue(), 0.01);

    assertEquals(25, s1.getTransShape().getShapeProperty().getOne());
    assertEquals(10, s1.getTransShape().getShapeProperty().getTwo());


    assertEquals("R", s2.getTransShape().getName());
    assertEquals(200, s2.getTransShape().getPosition().getX());
    assertEquals(200, s2.getTransShape().getPosition().getY());

    assertEquals(1, s2.getTransShape().getColor().getRed(), 0.01);
    assertEquals(0, s2.getTransShape().getColor().getGreen(), 0.01);
    assertEquals(0, s2.getTransShape().getColor().getBlue(), 0.01);

    assertEquals(25, s2.getTransShape().getShapeProperty().getOne());
    assertEquals(100, s2.getTransShape().getShapeProperty().getTwo());
  }

  @Test
  public void testIllegalScale1() {
    try {
      IShape n = null;
      illegalS = new Scale(n, new TimePeriod(10, 50), new ShapeProperty(25, 10));
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("Parameters can not be null.", e.getMessage());
    }
  }

  @Test
  public void testIllegalScale2() {
    try {
      TimePeriod t = null;
      illegalS = new Scale(c.copyShape(), t, new ShapeProperty(25, 10));
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("Parameters can not be null.", e.getMessage());
    }
  }

  @Test
  public void testIllegalScale3() {
    try {
      ShapeProperty s = null;
      illegalS = new Scale(c.copyShape(), new TimePeriod(10, 50), s);
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid shaper property parameter.", e.getMessage());
    }
  }

  @Test
  public void testIllegalScale4() {
    try {
      illegalS = new Scale(c.copyShape(), new TimePeriod(10, 50),
          new ShapeProperty(60, 30));
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid shaper property parameter.", e.getMessage());
    }
  }

}

