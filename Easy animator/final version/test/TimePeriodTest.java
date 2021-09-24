import org.junit.Before;
import org.junit.Test;

import cs5004.animator.model.TimePeriod;

import static org.junit.Assert.assertEquals;

/**
 * A Junit test for period.
 */
public class TimePeriodTest {
  private TimePeriod p1;

  @Before
  public void setup() {
    p1 = new TimePeriod(10, 20);
  }

  @Test
  public void testGetStart() {
    assertEquals(10, p1.getStart());
  }

  @Test
  public void testGetEnd() {
    assertEquals(20, p1.getEnd());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalP1() {
    TimePeriod p1 = new TimePeriod(-200, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalP2() {
    TimePeriod p2 = new TimePeriod(100, -50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalP3() {
    TimePeriod p3 = new TimePeriod(200, 100);
  }
}
