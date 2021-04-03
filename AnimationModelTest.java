import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AnimationModelTest {

  private IShape R;
  private IShape C;
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
    ITransformation moveR = new Move(R, new TimePeriod(10, 50),
        new Point2D(300.0, 300.0));

    ITransformation scaleR = new Scale(R, new TimePeriod(51, 70),
        new ShapeProperty(25.0, 100.0));

    ITransformation moveR2 = new Move(R, new TimePeriod(70, 100),
        new Point2D(200.0, 200.0));

    ITransformation moveC = new Move(C, new TimePeriod(20, 70),
        new Point2D(500.0, 400.0));

    ITransformation changeColorC = new ChangeColor(C, new TimePeriod(50, 80),
        new ColorRGB(0.0, 1.0, 0.0));

    model.addTransformations(moveR);
    model.addTransformations(scaleR);
    model.addTransformations(moveR2);
    model.addTransformations(moveC);
    model.addTransformations(changeColorC);

    System.out.println(model.getTransInfo());
  }
}