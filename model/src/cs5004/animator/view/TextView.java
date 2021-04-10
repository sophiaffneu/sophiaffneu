package cs5004.animator.view;

import java.awt.*;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import cs5004.animator.model.*;
import cs5004.animator.model.Rectangle;

public class TextView {
  private IAnimator model;

  public TextView(IAnimator model) {
    this.model = model;
  }

  public void getText() {
    String output = "";
    for (IShape shape : model.getShapeList()) {
      output += shape.toString() + "\n";
    }
    output += "\n\n";
    for (IShape shape : model.getShapeList()) {
      output += shape.getPeriodInfo() + "\n";
    }

    output += "\n\n";

    List<ITransformation> sortedList = model.getTransList().stream().sorted(Comparator.comparingInt(t ->
        t.getTimePeriod().getStart())).collect(Collectors.toList());
    for (ITransformation t : sortedList) {
      output += t.toString() + "\n";
    }
    System.out.println(output);
  }


  public static void main(String[] args) {
    IAnimator model = new AnimationModel();
    Rectangle r = new Rectangle("R", new Point2D(200, 200),
        new Color(1, 0, 0), new ShapeProperty(50, 100),
        new TimePeriod(1, 100));
    Oval c = new Oval("C", new Point2D(500, 100),
        new Color(0, 0, 1), new ShapeProperty(60, 30),
        new TimePeriod(6, 100));

    model.addShape(r);
    model.addShape(c);

    IShape copyR = r.copyShape();
    IShape copyC = c.copyShape();

    ITransformation moveR = new Move(copyR, new TimePeriod(10, 50),
        new Point2D(300, 300));

    ITransformation scaleR = new Scale(copyR, new TimePeriod(51, 70),
        new ShapeProperty(25, 100));

    ITransformation moveC = new Move(copyC, new TimePeriod(20, 70),
        new Point2D(500, 400));

    ITransformation moveR1 = new Move(copyR, new TimePeriod(70, 100),
        new Point2D(200, 200));
    ITransformation changeColorC = new ChangeColor(copyC, new TimePeriod(50, 80),
        new Color(0, 1, 0));

    model.addTransformations(moveR);
    model.addTransformations(moveR1);
    model.addTransformations(scaleR);
    model.addTransformations(moveC);
    model.addTransformations(changeColorC);

    TextView text = new TextView(model);
    text.getText();
  }
}
