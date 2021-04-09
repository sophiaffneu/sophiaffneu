import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import cs5004.animator.model.*;
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
    r = new Rectangle("R", new Point2D(200.0, 200.0),
        new ColorRGB(1.0, 0.0, 0.0), new ShapeProperty(50, 100),
        new TimePeriod(1, 100));
    IShape c = new Oval("C", new Point2D(500.0, 100.0),
        new ColorRGB(0.0, 0.0, 1.0), new ShapeProperty(60, 30),
        new TimePeriod(6, 100));
    c1 = new ChangeColor(r.copyShape(), new TimePeriod(10, 50),
        new ColorRGB(0.0, 1.0, 0.0));
    c2 = new ChangeColor(c.copyShape(), new TimePeriod(50, 80),
        new ColorRGB(1.0, 1.0, 0.0));
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
    assertEquals(200, c1.getTransShape().getPosition().getX(), 0.01);
    assertEquals(200, c1.getTransShape().getPosition().getY(), 0.01);

    assertEquals(0, c1.getTransShape().getColor().getR(), 0.01);
    assertEquals(1, c1.getTransShape().getColor().getG(), 0.01);
    assertEquals(0, c1.getTransShape().getColor().getB(), 0.01);

    assertEquals(50, c1.getTransShape().getShapeProperty().getOne(), 0.01);
    assertEquals(100, c1.getTransShape().getShapeProperty().getTwo(), 0.01);

    assertEquals(1, c1.getTransShape().getPeriod().getStart());
    assertEquals(100, c1.getTransShape().getPeriod().getEnd());

    assertEquals("C", c2.getTransShape().getName());
    assertEquals(500, c2.getTransShape().getPosition().getX(), 0.01);
    assertEquals(100, c2.getTransShape().getPosition().getY(), 0.01);

    assertEquals(1, c2.getTransShape().getColor().getR(), 0.01);
    assertEquals(1, c2.getTransShape().getColor().getG(), 0.01);
    assertEquals(0, c2.getTransShape().getColor().getB(), 0.01);

    assertEquals(60, c2.getTransShape().getShapeProperty().getOne(), 0.01);
    assertEquals(30, c2.getTransShape().getShapeProperty().getTwo(), 0.01);

    assertEquals(6, c2.getTransShape().getPeriod().getStart());
    assertEquals(100, c2.getTransShape().getPeriod().getEnd());


  }

  @Test
  public void testIllegalColor1() {
    try {
      IShape n = null;
      illegalC = new ChangeColor(n, new TimePeriod(10, 50), new ColorRGB(0.0, 1.0, 0.0));
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("Parameters can not be null.", e.getMessage());
    }
  }

  @Test
  public void testIllegalColor2() {
    try {
      TimePeriod t = null;
      illegalC = new ChangeColor(r.copyShape(), t, new ColorRGB(0.0, 1.0, 0.0));
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("Parameters can not be null.", e.getMessage());
    }
  }

  @Test
  public void testIllegalColor3() {
    try {
      ColorRGB p = null;
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
          new ColorRGB(1.0, 0.0, 0.0));
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid color parameter.", e.getMessage());
    }
  }

  @Test
  public void testIllegalColor5() {
    try {
      illegalC = new ChangeColor(r.copyShape(), new TimePeriod(10, 50),
          new ColorRGB(1.0, 0.0, 0.0));
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid color parameter.", e.getMessage());
    }
  }

  @Test
  public void testIllegalColor6() {
    try {
      illegalC = new ChangeColor(r.copyShape(), new TimePeriod(70, 120),
          new ColorRGB(0.0, 1.0, 0.0));
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("The transformation can only happen when the shape is present.",
          e.getMessage());
    }
  }

  @Test
  public void testIllegalColor7() {
    try {
      illegalC = new ChangeColor(r.copyShape(), new TimePeriod(0, 50),
          new ColorRGB(0.0, 1.0, 0.0));
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("The transformation can only happen when the shape is present.",
          e.getMessage());
    }
  }

  @Test
  public void testIllegalColor8() {
    try {
      illegalC = new ChangeColor(r.copyShape(), new TimePeriod(0, 1),
          new ColorRGB(0.0, 1.0, 0.0));
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("The transformation can only happen when the shape is present.",
          e.getMessage());
    }
  }


  @Test
  public void testIllegalColor9() {
    try {
      illegalC = new ChangeColor(r.copyShape(), new TimePeriod(110, 150),
          new ColorRGB(0.0, 1.0, 0.0));
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("The transformation can only happen when the shape is present.",
          e.getMessage());
    }
  }
}

