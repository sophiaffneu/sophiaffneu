package cs5004.animator.view;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import cs5004.animator.model.*;

public class TextView {
  private IAnimator model;

  public TextView(IAnimator model){
    this.model = model;
  }

  public void getText(){
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


  public static void main(String[] args){
    IAnimator model = new AnimationModel();
    Rectangle r = new Rectangle("R", new Point2D(200.0, 200.0),
        new ColorRGB(1.0, 0.0, 0.0), new ShapeProperty(50, 100),
        new TimePeriod(1, 100));
    Oval c = new Oval("C", new Point2D(500.0, 100.0),
        new ColorRGB(0.0, 0.0, 1.0), new ShapeProperty(60, 30),
        new TimePeriod(6, 100));

    model.addShape(r);
    model.addShape(c);

    ITransformation moveR = new Move(r, new TimePeriod(10, 50),
        new Point2D(300.0, 300.0));

    ITransformation scaleR = new Scale(r, new TimePeriod(51, 70),
        new ShapeProperty(25.0, 100.0));

    ITransformation moveR1 = new Move(r, new TimePeriod(51, 69),
        new Point2D(200.0, 200.0));

    ITransformation moveC = new Move(c, new TimePeriod(20, 70),
        new Point2D(500.0, 400.0));

    ITransformation changeColorC = new ChangeColor(c, new TimePeriod(50, 80),
        new ColorRGB(0.0, 1.0, 0.0));

    model.addTransformations(moveR);
    model.addTransformations(moveR1);
    model.addTransformations(scaleR);
    model.addTransformations(moveC);
    model.addTransformations(changeColorC);

    TextView text = new TextView(model);
    text.getText();
  }
}
