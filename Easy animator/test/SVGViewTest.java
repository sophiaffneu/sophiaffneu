import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.util.Comparator;
import java.util.stream.Collectors;

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
import cs5004.animator.view.SVGView;

import static org.junit.Assert.assertEquals;

/**
 * A Junit test for svg view.
 */
public class SVGViewTest {
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
  public void testSVGViewInfo() {

    IView view = new SVGView(model.getShapeList(), model.getTransList().stream()
        .sorted(Comparator.comparingInt(t ->
            t.getTimePeriod().getStart())).collect(Collectors.toList()), 10,
        model.getWidth(), model.getHeight(), model.getX(), model.getY());
    view.play();
    assertEquals("<svg width=\"1000\" height=\"1000\" version=\"1.1\"\n"
        + "     xmlns=\"http://www.w3.org/2000/svg\">\n"
        + "\n"
        + "<rect id=\"R\" x=\"200\" y=\"200\" width=\"50\" height=\"100\" fill=\"rgb(255,0,"
        + "0)\" visibility=\"visible\" >\n"
        + "    <animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"4000.0ms\" attribute"
        + "Name=\"y\" from=\"200\" to=\"300\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"5100.0ms\" dur=\"1900.0ms\" attribute"
        + "Name=\"width\" from=\"50\" to=\"25\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"7000.0ms\" dur=\"3000.0ms\" attribute"
        + "Name=\"y\" from=\"300\" to=\"200\" fill=\"freeze\" />\n"

        + "</rect>\n"
        + "\n"
        + "<ellipse id=\"C\" cx=\"500\" cy=\"100\" rx=\"60\" ry=\"30\" fill=\"rgb(0,0,"
        + "255)\" visibility=\"visible\" >\n"
        + "    <animate attributeType=\"xml\" begin=\"2000.0ms\" dur=\"5000.0ms\" attribute"
        + "Name=\"cy\" from=\"100\" to=\"400\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"css\" begin=\"5000.0ms\" dur=\"3000.0ms\" attributeName"
        + "=\"fill\" from=\"rgb(0,0,255)\" to=\"rgb(0,255,0)\" fill=\"freeze\" />\n"
        + "</ellipse>\n"
        + "\n"
        + "</svg>", view.getOutPut());
  }

  @Test
  public void testGetViewType() {
    IView view = new SVGView(model.getShapeList(), model.getTransList(), 10,
        model.getWidth(), model.getHeight(), model.getX(), model.getY());
    assertEquals("svg", view.getViewType());
  }
}
