import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ScaleTest {
  private ITransformation m;
  IShape R = new Rectangle("R", new Point2D(200.0, 200.0),
          new ColorRGB(1.0, 0.0, 0.0), new ShapeProperty(50, 100),
          new TimePeriod(1, 100));
  IShape C = new Oval("C", new Point2D(500.0, 100.0),
          new ColorRGB(0.0, 0.0, 1.0), new ShapeProperty(60, 30),
          new TimePeriod(6, 100));

  @Test
  public void testScale() {
    m = new Scale(C.copyShape(), new TimePeriod(10, 50), new ShapeProperty(25.0, 10.0));

    assertEquals(TransType.SCALE, m.getTransType());
    assertEquals(10, m.getTimePeriod().getStart());
    assertEquals(50, m.getTimePeriod().getEnd());

    assertEquals("C", m.getTransShape().getName());

    assertEquals(25, m.getTransShape().getShapeProperty().getOne(), 0.01);
    assertEquals(10, m.getTransShape().getShapeProperty().getTwo(), 0.01);

    try {
      IShape n = null;
      m = new Scale(n, new TimePeriod(10, 50), new ShapeProperty(25.0, 10.0));
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("Parameters can not be null.", e.getMessage());
    }

    try {
      TimePeriod t = null;
      m = new Scale(C.copyShape(), t, new ShapeProperty(25.0, 10.0));
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("Parameters can not be null.", e.getMessage());
    }
    try {
      ShapeProperty s = null;
      m = new Scale(C.copyShape(), new TimePeriod(10, 50), s);
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid shaper property parameter.", e.getMessage());
    }

    try {
      m = new Scale(C.copyShape(), new TimePeriod(10, 50), new ShapeProperty(60.0, 30.0));
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid shaper property parameter.", e.getMessage());
    }

    try {
      m = new Scale(R.copyShape(), new TimePeriod(70, 120), new ShapeProperty(25.0, 10.0));
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("The transformation can only happen when the shape is present.",
              e.getMessage());
    }

    try {
      m = new Scale(R.copyShape(), new TimePeriod(0, 50), new ShapeProperty(25.0, 10.0));
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("The transformation can only happen when the shape is present.",
              e.getMessage());
    }
    try {
      m = new Scale(R.copyShape(), new TimePeriod(0, 5), new ShapeProperty(25.0, 10.0));
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("The transformation can only happen when the shape is present.",
              e.getMessage());
    }

    try {
      m = new Scale(R.copyShape(), new TimePeriod(110, 150), new ShapeProperty(25.0, 10.0));
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("The transformation can only happen when the shape is present.",
              e.getMessage());
    }
  }
}
