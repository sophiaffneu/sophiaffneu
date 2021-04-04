import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class AnimationModelTest {

  private IShape R;
  private IShape C;
  private IShape copyR;
  private IShape copyC;
  private IAnimator model;

  @Before
  public void setup() {
    model = new AnimationModel();
    R = new Rectangle("R", new Point2D(200.0, 200.0),
            new ColorRGB(1.0, 0.0, 0.0), new ShapeProperty(50, 100),
            new TimePeriod(1, 100));
    C = new Oval("C", new Point2D(500.0, 100.0),
            new ColorRGB(0.0, 0.0, 1.0), new ShapeProperty(60, 30),
            new TimePeriod(6, 100));

    copyR = R.copyShape();
    copyC = C.copyShape();
  }

  @Test
  public void testShapeListOperations() {
    model.addShape(R);
    model.addShape(C);
    List<String> namelist = model.getShapeList().stream().map(s -> s.getName()).
            collect(Collectors.toList());
    assertEquals("R", namelist.get(0).toString());
    assertEquals("C", namelist.get(1).toString());
    assertTrue(model.getShapeList().contains(R));
    assertTrue(model.getShapeList().contains(C));
    model.removeShape(R);
    assertFalse(model.getShapeList().contains(R));
    assertTrue(model.getShapeList().contains(C));

    try {
      model.addShape(C);
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("Shape already exist in list.", e.getMessage());
    }
    try {
      IShape N = null;
      model.addShape(N);
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("Shape can't be null.", e.getMessage());
    }

    try {
      IShape N = null;
      model.removeShape(N);
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("Shape can't be null.", e.getMessage());
    }

    try {
      model.removeShape(R);
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("The list doesn't have the shape to be removed.", e.getMessage());
    }
  }

  @Test
  public void testTransListOperations() {
    ITransformation moveR = new Move(copyR, new TimePeriod(10, 50),
            new Point2D(300.0, 300.0));

    ITransformation moveR1 = new Move(copyR, new TimePeriod(51, 69),
            new Point2D(200.0, 200.0));

    ITransformation moveR2 = new Move(copyR, new TimePeriod(20, 30),
            new Point2D(100.0, 100.0));

    ITransformation scaleR = new Scale(copyR, new TimePeriod(51, 70),
            new ShapeProperty(25.0, 100.0));

    ITransformation moveC = new Move(copyC, new TimePeriod(20, 70),
            new Point2D(500.0, 400.0));

    ITransformation changeColorC = new ChangeColor(copyC, new TimePeriod(50, 80),
            new ColorRGB(0.0, 1.0, 0.0));

    ITransformation changeColorC1 = new ChangeColor(copyC, new TimePeriod(50, 80),
            new ColorRGB(0.0, 1.0, 1.0));

    model.addTransformations(moveR);
    assertTrue(model.getTransList().contains(moveR));
    assertFalse(model.getTransList().contains(moveR1));

    model.addTransformations(moveR1);
    assertTrue(model.getTransList().contains(moveR1));

    model.addTransformations(scaleR);
    assertTrue(model.getTransList().contains(scaleR));

    model.addTransformations(moveC);
    assertTrue(model.getTransList().contains(moveC));
    model.removeTransformations(moveC);
    assertTrue(!model.getTransList().contains(moveC));

    model.addTransformations(changeColorC);
    assertTrue(model.getTransList().contains(changeColorC));
    model.removeTransformations(changeColorC);
    assertTrue(!model.getTransList().contains(changeColorC));

    model.removeTransformations(moveR1);
    assertFalse(model.getTransList().contains(moveR1));

    model.addTransformations(changeColorC);

    assertEquals(moveR, model.getTransList().get(0));
    assertEquals(scaleR, model.getTransList().get(1));
    assertEquals(changeColorC, model.getTransList().get(2));

    try {
      model.addTransformations(moveR);
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("Same type of transformation with overlapping time period " +
              "already exist.", e.getMessage());
    }

    try {
      model.addTransformations(moveR2);
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("Same type of transformation with overlapping time period " +
              "already exist.", e.getMessage());
    }
    try {
      ITransformation n = null;
      model.addTransformations(n);
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("Transformation can't be null.", e.getMessage());
    }

    try {
      ITransformation n = null;
      model.removeTransformations(n);
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("Parameter can't be null.", e.getMessage());
    }

    try {
      model.removeTransformations(moveR1);
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("The list doesn't have the transformation to be removed.",
              e.getMessage());
    }
  }


  @Test
  public void testGetShapesInfo() {
    model.addShape(R);
    model.addShape(C);
    assertEquals("Shapes:\nName: R\nType: rectangle\nMin corner: (200.0,200.0),"
            + " Width: 50.0, Height: 100.0, Color: (1.0,0.0,0.0)\n"
            + "Appears at t=1\nDisappears at t=100\n\n"
            + "Name: C\nType: oval\nCenter: (500.0,100.0), X radius: 60.0," +
            " Y radius: 30.0, Color: (0.0,0.0,1.0)\n" +
            "Appears at t=6\nDisappears at t=100\n\n", model.getShapesInfo());
  }

  @Test
  public void testGetTransInfo() {
    ITransformation moveR = new Move(copyR, new TimePeriod(10, 50),
            new Point2D(300.0, 300.0));

    ITransformation scaleR = new Scale(copyR, new TimePeriod(51, 70),
            new ShapeProperty(25.0, 100.0));

    ITransformation moveR1 = new Move(copyR, new TimePeriod(51, 69),
            new Point2D(200.0, 200.0));

    ITransformation moveC = new Move(copyC, new TimePeriod(20, 70),
            new Point2D(500.0, 400.0));

    ITransformation changeColorC = new ChangeColor(copyC, new TimePeriod(50, 80),
            new ColorRGB(0.0, 1.0, 0.0));

    model.addTransformations(moveR);
    model.addTransformations(moveR1);
    model.addTransformations(scaleR);
    model.addTransformations(moveC);
    model.addTransformations(changeColorC);

    assertEquals("Shape R moves from (200.0,200.0) to (300.0,300.0) from t=10 to t=50\n" +
            "Shape C moves from (500.0,100.0) to (500.0,400.0) from t=20 to t=70\n" +
            "Shape C changes color from (0.0,0.0,1.0) to (0.0,1.0,0.0) from t=50 to t=80\n" +
            "Shape R moves from (300.0,300.0) to (200.0,200.0) from t=51 to t=69\n" +
            "Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, " +
            "Height: 100.0 from t=51 to t=70\n", model.getTransInfo());
  }
}