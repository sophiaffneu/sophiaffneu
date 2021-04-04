import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * JUnit test for shape.
 */
public class ShapeTest {

  private IShape r1;
  private IShape o1;

  @Before
  public void setup() {
    r1 = new Rectangle("R", new Point2D(200, 200), new ColorRGB(1, 0, 0),
        new ShapeProperty(50, 100), new TimePeriod(1, 100));

    o1 = new Oval("C", new Point2D(500, 100), new ColorRGB(0, 0, 1),
        new ShapeProperty(60, 30), new TimePeriod(6, 100));
  }

  @Test
  public void testToString() {
    assertEquals("Name: R\nType: rectangle\nMin corner: (200.0,200.0), " +
        "Width: 50.0, Height: 100.0, Color: (1.0,0.0,0.0)\n" +
        "Appears at t=1\nDisappears at t=100\n", r1.toString());

    assertEquals("Name: C\nType: oval\nCenter: (500.0,100.0), X radius: 60.0, " +
        "Y radius: 30.0, Color: (0.0,0.0,1.0)\n" +
        "Appears at t=6\nDisappears at t=100\n", o1.toString());
  }

  @Test
  public void testGetName(){
    assertEquals("R",r1.getName());
    assertEquals("C",o1.getName());
  }

  @Test
  public void testPosition(){
    assertEquals(200,r1.getPosition().getX(),0.01);
    assertEquals(200,r1.getPosition().getY(),0.01);
    r1.setPosition(new Point2D(500,600));
    assertEquals(500,r1.getPosition().getX(),0.01);
    assertEquals(600,r1.getPosition().getY(),0.01);
  }

  @Test
  public void testColor(){
    assertEquals(1.0,r1.getColor().getR(),0.01);
    assertEquals(0,r1.getColor().getG(),0.01);
    assertEquals(0,r1.getColor().getB(),0.01);
    r1.setColor(new ColorRGB(0,1,1));
    assertEquals(0,r1.getColor().getR(),0.01);
    assertEquals(1,r1.getColor().getG(),0.01);
    assertEquals(1,r1.getColor().getB(),0.01);
  }

  @Test
  public void testShapeProperty(){
    assertEquals(50,r1.getShapeProperty().getOne(),0.01);
    assertEquals(100,r1.getShapeProperty().getTwo(),0.01);
    r1.setShapeProperty(new ShapeProperty(200,300));
    assertEquals(200,r1.getShapeProperty().getOne(),0.01);
    assertEquals(300,r1.getShapeProperty().getTwo(),0.01);
  }

  @Test
  public void testTimePeriod(){
    assertEquals(1,r1.getPeriod().getStart());
    assertEquals(100,r1.getPeriod().getEnd());
    r1.setPeriod(new TimePeriod(43,52));
    assertEquals(43,r1.getPeriod().getStart());
    assertEquals(52,r1.getPeriod().getEnd());
  }

  @Test
  public void testGetShapeType(){
    assertEquals(ShapeType.RECTANGLE,r1.getShapeType());
    assertEquals(ShapeType.OVAL,o1.getShapeType());
  }

  @Test
  public void testCopyShape(){
    IShape copy = r1.copyShape();
    assertTrue(copy.getName().equals(r1.getName()));
    assertEquals(copy.getPosition().getX(),r1.getPosition().getX(),0.01);
    assertEquals(copy.getPosition().getY(),r1.getPosition().getY(),0.01);
    assertEquals(copy.getColor().getR(),r1.getColor().getR(),0.01);
    assertEquals(copy.getColor().getG(),r1.getColor().getG(),0.01);
    assertEquals(copy.getColor().getB(),r1.getColor().getB(),0.01);
    assertEquals(copy.getShapeType(),r1.getShapeType());
    assertEquals(copy.getPeriod().getStart(),r1.getPeriod().getStart());
    assertEquals(copy.getPeriod().getEnd(),r1.getPeriod().getEnd());
  }
}

