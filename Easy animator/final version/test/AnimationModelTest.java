import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.util.List;
import java.util.stream.Collectors;


import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.ChangeColor;
import cs5004.animator.model.IAnimator;
import cs5004.animator.model.IShape;
import cs5004.animator.model.ITransformation;
import cs5004.animator.model.Move;
import cs5004.animator.model.Oval;
import cs5004.animator.model.Point2D;
import cs5004.animator.model.Rectangle;
import cs5004.animator.model.Scale;
import cs5004.animator.model.ShapeProperty;
import cs5004.animator.model.TimePeriod;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * A JUnit test for the animationModel.
 */
public class AnimationModelTest {

  private IShape r;
  private IShape c;
  private IShape copyR;
  private IShape copyC;
  private IAnimator model;

  @Before
  public void setup() {
    model = new AnimationModel();
    r = new Rectangle("R", new Point2D(200, 200),
        new Color(1, 0, 0), new ShapeProperty(50, 100));
    c = new Oval("C", new Point2D(500, 100),
        new Color(0, 0, 1), new ShapeProperty(60, 30));

    copyR = r.copyShape();
    copyC = c.copyShape();
  }

  @Test
  public void testShapeListOperations() {
    model.addShape(r);
    model.addShape(c);
    List<String> namelist = model.getShapeList().stream().map(IShape::getName)
        .collect(Collectors.toList());
    assertEquals("R", namelist.get(0));
    assertEquals("C", namelist.get(1));
    assertTrue(model.getShapeList().contains(r));
    assertTrue(model.getShapeList().contains(c));
    model.removeShape(r);
    assertFalse(model.getShapeList().contains(r));
    assertTrue(model.getShapeList().contains(c));

    try {
      model.addShape(c);
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("Shape already exist in list.", e.getMessage());
    }
    try {
      IShape n = null;
      model.addShape(n);
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("Shape can't be null.", e.getMessage());
    }

    try {
      IShape n = null;
      model.removeShape(n);
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("Shape can't be null.", e.getMessage());
    }

    try {
      model.removeShape(r);
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("The list doesn't have the shape to be removed.", e.getMessage());
    }
  }

  @Test
  public void testTransListOperations() {
    ITransformation moveR = new Move(copyR, new TimePeriod(10, 50),
        new Point2D(300, 300));

    ITransformation moveR1 = new Move(copyR, new TimePeriod(51, 69),
        new Point2D(200, 200));

    ITransformation moveR2 = new Move(copyR, new TimePeriod(20, 30),
        new Point2D(100, 100));

    ITransformation scaleR = new Scale(copyR, new TimePeriod(51, 70),
        new ShapeProperty(25, 100));

    ITransformation moveC = new Move(copyC, new TimePeriod(20, 70),
        new Point2D(500, 400));

    ITransformation changeColorC = new ChangeColor(copyC, new TimePeriod(50, 80),
        new Color(0, 1, 0));

    ITransformation changeColorC1 = new ChangeColor(copyC, new TimePeriod(50, 80),
        new Color(0, 1, 1));

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
    assertFalse(model.getTransList().contains(moveC));

    model.addTransformations(changeColorC);
    assertTrue(model.getTransList().contains(changeColorC));
    model.removeTransformations(changeColorC);
    assertFalse(model.getTransList().contains(changeColorC));

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
      assertEquals("Same type of transformation with overlapping time period "
          + "already exist.", e.getMessage());
    }

    try {
      model.addTransformations(moveR2);
      fail("Invalid input should have thrown exception.");
    } catch (IllegalArgumentException e) {
      assertEquals("Same type of transformation with overlapping time period "
          + "already exist.", e.getMessage());
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

}