import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A Junit test for shapeProperty.
 */
public class ShapePropertyTest {
  private ShapeProperty s1;

  @Before
  public void setup(){
    s1 = new ShapeProperty(50,80);
  }

  @Test
  public void testGetOne(){
    assertEquals(50,s1.getOne(),0.01);
  }

  @Test
  public void testGetTwo(){
    assertEquals(80,s1.getTwo(),0.01);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalSize1(){
    ShapeProperty s1 = new ShapeProperty(-50,100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalSize2(){
    ShapeProperty s2 = new ShapeProperty(50,-100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalSize3(){
    ShapeProperty s3 = new ShapeProperty(-50,-100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalSize4(){
    ShapeProperty s3 = new ShapeProperty(0,100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalSize5(){
    ShapeProperty s3 = new ShapeProperty(50,0);
  }
}
