import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class MoveTest {
  private ITransformation m;
  IShape R = new Rectangle("R", new Point2D(200.0, 200.0),
          new ColorRGB(1.0, 0.0, 0.0), new ShapeProperty(50, 100),
          new TimePeriod(1, 100));
  IShape C = new Oval("C", new Point2D(500.0, 100.0),
          new ColorRGB(0.0, 0.0, 1.0), new ShapeProperty(60, 30),
          new TimePeriod(6, 100));

  @Test
  public void testMove() {
    m = new Move(R.copyShape(), new TimePeriod(10, 50), new Point2D(300, 300));

    assertEquals(TransType.MOVE, m.getTransType());
    assertEquals(10, m.getTimePeriod().getStart());
    assertEquals(50, m.getTimePeriod().getEnd());

    assertEquals("R", m.getTransShape().getName());
    assertEquals(300, m.getTransShape().getPosition().getX(), 0.01);
    assertEquals(300, m.getTransShape().getPosition().getY(), 0.01);

    assertEquals(1.0, m.getTransShape().getColor().getR(), 0.01);
    assertEquals(0, m.getTransShape().getColor().getG(), 0.01);
    assertEquals(0, m.getTransShape().getColor().getB(), 0.01);

    assertEquals(50, m.getTransShape().getShapeProperty().getOne(), 0.01);
    assertEquals(100, m.getTransShape().getShapeProperty().getTwo(), 0.01);

    assertEquals(10, m.getTimePeriod().getStart());
    assertEquals(50, m.getTimePeriod().getEnd());

    try {
      IShape n = null;
      m = new Move(n, new TimePeriod(10, 50), new Point2D(300, 300));
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("Parameters can not be null.", e.getMessage());
    }

    try {
      TimePeriod t = null;
      m = new Move(R.copyShape(), t, new Point2D(300, 300));
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("Parameters can not be null.", e.getMessage());
    }
    try {
      Point2D p = null;
      m = new Move(R.copyShape(), new TimePeriod(10, 50), p);
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid toPosition parameter.", e.getMessage());
    }

    try {
      m = new Move(R.copyShape(), new TimePeriod(10, 50), new Point2D(200.0, 200.0));
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid toPosition parameter.", e.getMessage());
    }

    try {
      m = new Move(R.copyShape(), new TimePeriod(70, 120), new Point2D(300, 300));
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("The transformation can only happen when the shape is present.",
              e.getMessage());
    }

    try {
      m = new Move(R.copyShape(), new TimePeriod(0, 50), new Point2D(300, 300));
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("The transformation can only happen when the shape is present.",
              e.getMessage());
    }

    try {
      m = new Move(R.copyShape(), new TimePeriod(0, 1), new Point2D(300, 300));
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("The transformation can only happen when the shape is present.",
              e.getMessage());
    }
    try {
      m = new Move(R.copyShape(), new TimePeriod(110, 150), new Point2D(300, 300));
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("The transformation can only happen when the shape is present.",
              e.getMessage());
    }
  }
}
