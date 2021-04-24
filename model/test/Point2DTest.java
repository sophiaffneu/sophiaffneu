import org.junit.Before;
import org.junit.Test;

import cs5004.animator.model.Point2D;

import static org.junit.Assert.assertEquals;

/**
 * A Junit test for cs5004.animator.model.Point2D.
 */
public class Point2DTest {
  private Point2D p1;

  @Before
  public void setup() {
    p1 = new Point2D(200, 200);
  }

  @Test
  public void testGetX() {
    assertEquals(200, p1.getX());
  }

  @Test
  public void testGetY() {
    assertEquals(200, p1.getY());
  }


  @Test
  public void testPointString() {
    assertEquals("(200,200)", p1.toString());
  }
}
