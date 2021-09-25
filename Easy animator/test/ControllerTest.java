import org.junit.Before;
import org.junit.Test;

import java.awt.Color;

import cs5004.animator.controller.AnimatorController;
import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.ChangeColor;
import cs5004.animator.model.IAnimator;
import cs5004.animator.model.IShape;
import cs5004.animator.model.ITransformation;
import cs5004.animator.model.Move;
import cs5004.animator.model.NoChange;
import cs5004.animator.model.Oval;
import cs5004.animator.model.Point2D;
import cs5004.animator.model.Rectangle;
import cs5004.animator.model.Scale;
import cs5004.animator.model.ShapeProperty;
import cs5004.animator.model.TimePeriod;
import cs5004.animator.view.IView;
import cs5004.animator.view.TextView;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit test for controller.
 */
public class ControllerTest {
  private IAnimator model;

  @Before
  public void setup() {
    model = new AnimationModel();
    model.setBounds(0, 0, 1000, 1000);
    Rectangle r = new Rectangle("R", new Point2D(200, 200),
        new Color(255, 0, 0), new ShapeProperty(50, 100));
    Oval c = new Oval("C", new Point2D(500, 100),
        new Color(0, 0, 255), new ShapeProperty(60, 30));

    IShape copyR = r.copyShape();
    IShape copyC = c.copyShape();

    model.addShape(r);
    model.addShape(c);

    ITransformation noR1 = new NoChange(copyR, new TimePeriod(1, 10));
    ITransformation moveR1 = new Move(copyR, new TimePeriod(10, 50), new Point2D(200, 300));
    ITransformation noR2 = new NoChange(copyR, new TimePeriod(50, 70));
    ITransformation moveR2 = new Move(copyR, new TimePeriod(70, 100), new Point2D(200, 200));
    ITransformation scaleR1 = new Scale(copyR, new TimePeriod(51, 70),
        new ShapeProperty(25, 100));

    ITransformation noC1 = new NoChange(copyC, new TimePeriod(6, 20));
    ITransformation moveC1 = new Move(copyC, new TimePeriod(20, 70), new Point2D(500, 400));
    ITransformation color1 = new ChangeColor(copyC, new TimePeriod(50, 80),
        new Color(0, 255, 0));
    ITransformation noC2 = new NoChange(copyC, new TimePeriod(90, 100));


    model.addTransformations(moveR1);
    model.addTransformations(moveR2);
    model.addTransformations(noC1);
    model.addTransformations(noR1);
    model.addTransformations(noR2);
    model.addTransformations(color1);
    model.addTransformations(scaleR1);
    model.addTransformations(moveC1);
    model.addTransformations(noC2);

  }

  @Test
  public void testGoToView() {
    IView view = new TextView(model.getShapeList(), model.getTransList());

    AnimatorController controller = new AnimatorController(view, model, 10);
    controller.goToView(view);

    assertEquals("Create (255,0,0) rectangle R with corner at (200,200"
        + "), width 50 and height 100\n"
        + "Create (0,0,255) oval C with center at (500,100), radius 60 and 30\n\n\n"
        + "R appears at time t=1 and disappears at time t=100\n"
        + "C appears at time t=6 and disappears at time t=100\n\n\n"
        + "R moves from (200,200) to (200,300) from t=10 to t=50\n"
        + "C moves from (500,100) to (500,400) from t=20 to t=70\n"
        + "C changes from (0,0,255) to (0,255,0) from t=50 to t=80\n"
        + "R changes width from 50 to 25 from t=51 to t=70\n"
        + "R moves from (200,300) to (200,200) from t=70 to t=100\n", view.getOutPut());

  }
}
