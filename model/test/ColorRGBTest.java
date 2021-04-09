import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import cs5004.animator.model.*;
/**
 * A JUnit test for colorRGB.
 */
public class ColorRGBTest {

  private ColorRGB c1;
  private ColorRGB c2;

  @Before
  public void setup() {
    c1 = new ColorRGB(1, 1, 1);
    c2 = new ColorRGB(0, 0, 0);
  }

  @Test
  public void testGetR() {
    assertEquals(1.0, c1.getR(), 0.01);
    assertEquals(0, c2.getR(), 0.01);
  }

  @Test
  public void testGetG() {
    assertEquals(1.0, c1.getG(), 0.01);
    assertEquals(0, c2.getG(), 0.01);
  }

  @Test
  public void testGetB() {
    assertEquals(1.0, c1.getB(), 0.01);
    assertEquals(0, c2.getB(), 0.01);
  }

  @Test
  public void testColorString() {
    assertEquals("(1.0,1.0,1.0)", c1.toString());
    assertEquals("(0.0,0.0,0.0)", c2.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalR1() {
    ColorRGB r1 = new ColorRGB(200, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalR2() {
    ColorRGB r2 = new ColorRGB(-200, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalG1() {
    ColorRGB g1 = new ColorRGB(0.5, 3.8, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalG2() {
    ColorRGB g2 = new ColorRGB(0.5, -3.8, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalB1() {
    ColorRGB b1 = new ColorRGB(0.3, 0.8, 1.2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalB2() {
    ColorRGB b2 = new ColorRGB(0.3, 0.8, -1.2);
  }
}
