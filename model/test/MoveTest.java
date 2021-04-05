import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * A JUnit test for move class.
 */
public class MoveTest {
  private ITransformation m1;
  private ITransformation m2;
  private ITransformation illegalM;
  private IShape r;

  @Before
  public void setup() {
    r = new Rectangle("R", new Point2D(200.0, 200.0),
        new ColorRGB(1.0, 0.0, 0.0), new ShapeProperty(50, 100),
        new TimePeriod(1, 100));
    IShape c = new Oval("C", new Point2D(500.0, 100.0),
        new ColorRGB(0.0, 0.0, 1.0), new ShapeProperty(60, 30),
        new TimePeriod(6, 100));
    m1 = new Move(r.copyShape(), new TimePeriod(10, 50), new Point2D(300, 300));
    m2 = new Move(c.copyShape(), new TimePeriod(20, 70), new Point2D(500, 400));
  }


  @Test
  public void testGetTransType() {
    assertEquals(TransType.MOVE, m1.getTransType());
    assertEquals(TransType.MOVE, m2.getTransType());
  }

  @Test
  public void testGetTransShape() {
    assertEquals("R", m1.getTransShape().getName());
    assertEquals(300, m1.getTransShape().getPosition().getX(), 0.01);
    assertEquals(300, m1.getTransShape().getPosition().getY(), 0.01);

    assertEquals(1.0, m1.getTransShape().getColor().getR(), 0.01);
    assertEquals(0, m1.getTransShape().getColor().getG(), 0.01);
    assertEquals(0, m1.getTransShape().getColor().getB(), 0.01);

    assertEquals(50, m1.getTransShape().getShapeProperty().getOne(), 0.01);
    assertEquals(100, m1.getTransShape().getShapeProperty().getTwo(), 0.01);

    assertEquals(1, m1.getTransShape().getPeriod().getStart());
    assertEquals(100, m1.getTransShape().getPeriod().getEnd());


    assertEquals("C", m2.getTransShape().getName());
    assertEquals(500, m2.getTransShape().getPosition().getX(), 0.01);
    assertEquals(400, m2.getTransShape().getPosition().getY(), 0.01);

    assertEquals(0, m2.getTransShape().getColor().getR(), 0.01);
    assertEquals(0, m2.getTransShape().getColor().getG(), 0.01);
    assertEquals(1, m2.getTransShape().getColor().getB(), 0.01);

    assertEquals(60, m2.getTransShape().getShapeProperty().getOne(), 0.01);
    assertEquals(30, m2.getTransShape().getShapeProperty().getTwo(), 0.01);

    assertEquals(6, m2.getTransShape().getPeriod().getStart());
    assertEquals(100, m2.getTransShape().getPeriod().getEnd());
  }

  @Test
  public void getTimePeriod() {
    assertEquals(10, m1.getTimePeriod().getStart());
    assertEquals(50, m1.getTimePeriod().getEnd());
    assertEquals(20, m2.getTimePeriod().getStart());
    assertEquals(70, m2.getTimePeriod().getEnd());
  }

  @Test
  public void testIllegalMove1() {
    try {
      IShape n = null;
      illegalM = new Move(n, new TimePeriod(10, 50), new Point2D(300, 300));
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("Parameters can not be null.", e.getMessage());
    }
  }

  @Test
  public void testIllegalMove2() {
    try {
      TimePeriod t = null;
      illegalM = new Move(r.copyShape(), t, new Point2D(300, 300));
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("Parameters can not be null.", e.getMessage());
    }
  }

  @Test
  public void testIllegalMove3() {
    try {
      Point2D p = null;
      illegalM = new Move(r.copyShape(), new TimePeriod(10, 50), p);
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid toPosition parameter.", e.getMessage());
    }
  }

  @Test
  public void testIllegalMove4() {
    try {
      illegalM = new Move(r.copyShape(), new TimePeriod(10, 50), new Point2D(200.0, 200.0));
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid toPosition parameter.", e.getMessage());
    }
  }

  @Test
  public void testIllegalMove5() {
    try {
      illegalM = new Move(r.copyShape(), new TimePeriod(70, 120), new Point2D(300, 300));
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("The transformation can only happen when the shape is present.",
          e.getMessage());
    }
  }

  @Test
  public void testIllegalMove6() {
    try {
      illegalM = new Move(r.copyShape(), new TimePeriod(0, 50), new Point2D(300, 300));
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("The transformation can only happen when the shape is present.",
          e.getMessage());
    }
  }

  @Test
  public void testIllegalMove7() {
    try {
      illegalM = new Move(r.copyShape(), new TimePeriod(0, 1), new Point2D(300, 300));
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("The transformation can only happen when the shape is present.",
          e.getMessage());
    }
  }

  @Test
  public void testIllegalMove8() {
    try {
      illegalM = new Move(r.copyShape(), new TimePeriod(110, 150), new Point2D(300, 300));
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("The transformation can only happen when the shape is present.",
          e.getMessage());
    }
  }
}
