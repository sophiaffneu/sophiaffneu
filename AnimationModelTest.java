import org.junit.Test;

import static org.junit.Assert.*;

public class AnimationModelTest {
  AnimationModel test = new AnimationModel();
  IShape R = new Rectangle("R", new Point2D(200.0, 200.0),
          new ColorRGB(1.0, 0.0, 0.0), new ShapeProperty(50, 100),
          new TimePeriod(1, 100));
  IShape C = new Oval("C", new Point2D(500.0, 100.0),
          new ColorRGB(0.0, 0.0, 1.0), new ShapeProperty(60, 30),
          new TimePeriod(6, 100));

  ITransformation moveR = new Move(R.copyShape(), new TimePeriod(10, 50),
          new Point2D(300.0, 300.0), new Point2D(200.0, 200.0));

  ITransformation scaleR = new Scale(R.copyShape(), new TimePeriod(51, 70),
          new ShapeProperty(25.0, 100.0), new ShapeProperty(50.0, 100.0));

  ITransformation moveR2 = new Move(R.copyShape(), new TimePeriod(70, 100),
          new Point2D(200.0, 200.0), new Point2D(300.0, 300.0));

  ITransformation moveC = new Move(C.copyShape(), new TimePeriod(20, 70),
          new Point2D(500.0, 400.0), new Point2D(500.0, 100.0));

  ITransformation changeColorC = new ChangeColor(C.copyShape(), new TimePeriod(50, 80),
          new ColorRGB(0.0, 1.0, 0.0), new ColorRGB(0.0, 0.0, 1.0));

  @Test
  public void testAnimationModel() {
    test.addShape(R);
    test.addShape(C);
    test.addTransformations(moveR);
    test.addTransformations(scaleR);
    test.addTransformations(moveR2);
    test.addTransformations(moveC);
    test.addTransformations(changeColorC);

    System.out.println(test.getTransInfo());
  }

}