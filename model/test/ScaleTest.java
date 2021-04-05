import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
    r = new Rectangle("R", new Point2D(200.0, 200.0),
        new ColorRGB(1.0, 0.0, 0.0), new ShapeProperty(50, 100),
        new TimePeriod(1, 100));
    c = new Oval("C", new Point2D(500.0, 100.0),
        new ColorRGB(0.0, 0.0, 1.0), new ShapeProperty(60, 30),
        new TimePeriod(6, 100));
    s1 = new Scale(c.copyShape(), new TimePeriod(10, 50), new ShapeProperty(25.0, 10.0));
    s2 = new Scale(r.copyShape(), new TimePeriod(51, 70), new ShapeProperty(25.0, 100.0));
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
    assertEquals(500, s1.getTransShape().getPosition().getX(), 0.01);
    assertEquals(100, s1.getTransShape().getPosition().getY(), 0.01);

    assertEquals(0, s1.getTransShape().getColor().getR(), 0.01);
    assertEquals(0, s1.getTransShape().getColor().getG(), 0.01);
    assertEquals(1, s1.getTransShape().getColor().getB(), 0.01);

    assertEquals(25, s1.getTransShape().getShapeProperty().getOne(), 0.01);
    assertEquals(10, s1.getTransShape().getShapeProperty().getTwo(), 0.01);

    assertEquals(6, s1.getTransShape().getPeriod().getStart());
    assertEquals(100, s1.getTransShape().getPeriod().getEnd());


    assertEquals("R", s2.getTransShape().getName());
    assertEquals(200, s2.getTransShape().getPosition().getX(), 0.01);
    assertEquals(200, s2.getTransShape().getPosition().getY(), 0.01);

    assertEquals(1, s2.getTransShape().getColor().getR(), 0.01);
    assertEquals(0, s2.getTransShape().getColor().getG(), 0.01);
    assertEquals(0, s2.getTransShape().getColor().getB(), 0.01);

    assertEquals(25, s2.getTransShape().getShapeProperty().getOne(), 0.01);
    assertEquals(100, s2.getTransShape().getShapeProperty().getTwo(), 0.01);

    assertEquals(1, s2.getTransShape().getPeriod().getStart());
    assertEquals(100, s2.getTransShape().getPeriod().getEnd());
  }

  @Test
  public void testIllegalScale1() {
    try {
      IShape n = null;
      illegalS = new Scale(n, new TimePeriod(10, 50), new ShapeProperty(25.0, 10.0));
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("Parameters can not be null.", e.getMessage());
    }
  }

  @Test
  public void testIllegalScale2() {
    try {
      TimePeriod t = null;
      illegalS = new Scale(c.copyShape(), t, new ShapeProperty(25.0, 10.0));
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
          new ShapeProperty(60.0, 30.0));
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid shaper property parameter.", e.getMessage());
    }
  }

  @Test
  public void testIllegalScale5() {
    try {
      illegalS = new Scale(r.copyShape(), new TimePeriod(70, 120),
          new ShapeProperty(25.0, 10.0));
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("The transformation can only happen when the shape is present.",
          e.getMessage());
    }

  }

  @Test
  public void testIllegalScale6() {
    try {
      illegalS = new Scale(r.copyShape(), new TimePeriod(0, 50),
          new ShapeProperty(25.0, 10.0));
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("The transformation can only happen when the shape is present.",
          e.getMessage());
    }
  }

  @Test
  public void testIllegalScale7() {
    try {
      illegalS = new Scale(r.copyShape(), new TimePeriod(0, 5),
          new ShapeProperty(25.0, 10.0));
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("The transformation can only happen when the shape is present.",
          e.getMessage());
    }
  }

  @Test
  public void testIllegalScale8() {
    try {
      illegalS = new Scale(r.copyShape(), new TimePeriod(110, 150),
          new ShapeProperty(25.0, 10.0));
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("The transformation can only happen when the shape is present.",
          e.getMessage());
    }
  }
}

