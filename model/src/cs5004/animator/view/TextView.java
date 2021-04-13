package cs5004.animator.view;

import java.awt.*;
import java.util.ArrayList;
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


    for (IShape s : model.getShapeList()) {
      List<ITransformation> singleList = new ArrayList<>();
      for (ITransformation t : model.getTransList()) {
        if (t.getTransShape().getName().equals(s.getName())
            && t.getTransShape().getShapeType() == s.getShapeType()) {
          singleList.add(t);
        }
      }
      List<ITransformation> sortedList = singleList.stream().sorted(Comparator.comparingInt(t ->
          t.getTimePeriod().getStart())).collect(Collectors.toList());

      int begin = sortedList.get(0).getTimePeriod().getStart();
      int end = sortedList.get(sortedList.size() - 1).getTimePeriod().getEnd();
      output += s.getName() + " appears at time t=" + begin + " and disappears at time t=" + end + "\n";
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
        new Color(255, 0, 0), new ShapeProperty(50, 100));
    Oval c = new Oval("C", new Point2D(500, 100),
        new Color(0, 0, 255), new ShapeProperty(60, 30));

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
        new Color(0, 255, 0));

    model.addTransformations(moveR);
    model.addTransformations(moveR1);
    model.addTransformations(scaleR);
    model.addTransformations(moveC);
    model.addTransformations(changeColorC);

    TextView text = new TextView(model);
    text.getText();
  }
}
