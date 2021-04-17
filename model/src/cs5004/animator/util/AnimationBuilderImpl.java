package cs5004.animator.util;


import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.ChangeColor;
import cs5004.animator.model.IShape;
import cs5004.animator.model.ITransformation;
import cs5004.animator.model.Move;
import cs5004.animator.model.NoChange;
import cs5004.animator.model.Oval;
import cs5004.animator.model.Point2D;
import cs5004.animator.model.Rectangle;
import cs5004.animator.model.Scale;
import cs5004.animator.model.ShapeProperty;
import cs5004.animator.model.ShapeType;
import cs5004.animator.model.TimePeriod;

public class AnimationBuilderImpl implements AnimationBuilder {
  AnimationModel m = new AnimationModel();
  @Override
  public AnimationModel build() {
    return m;
  }

  @Override
  public AnimationBuilder setBounds(int x, int y, int width, int height) {
    m.setBounds(x, y, width, height);
    return this;
  }

  @Override
  public AnimationBuilder declareShape(String name, String type) {
    IShape nShape;
    if (type.equals(ShapeType.RECTANGLE.toString())) {
      nShape = new Rectangle(name);
    } else {
      nShape = new Oval(name);
    }
    m.addShape(nShape);
    //for (IShape n : m.getShapeList()) {
    //System.out.println(n.getName().toString());
    //}
    return this;
  }

  @Override
  public AnimationBuilder addMotion(String name, int t1, int x1, int y1, int w1, int h1, int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2) {
    for (IShape n : m.getShapeList()) {

      if (n.getName().equals(name)) {

        if (n.getPosition() == null && n.getShapeProperty() == null && n.getColor() == null) {
          n.setPosition(new Point2D(x1, y1));
          n.setShapeProperty(new ShapeProperty(w1, h1));
          n.setColor(new Color(r1, g1, b1));


          //if (m.getTransList().size() == 0 || (m.getTransList().size() > 0 &&
          // m.getTransList().stream().filter(t -> t.getTransShape().getName().equals(n.getName())).count() == 0)) {
          IShape copyS = n.copyShape();

          if (x1 != x2 || y1 != y2) {

            ITransformation newMove = new Move(copyS, new TimePeriod(t1, t2), new Point2D(x2, y2));

            m.addTransformations(newMove);
          }
          if (w1 != w2 || h1 != h2) {
            ITransformation newScale = new Scale(copyS, new TimePeriod(t1, t2), new ShapeProperty(w2, h2));

            m.addTransformations(newScale);
          }
          if (r1 != r2 || g1 != g2 || b1 != b2) {

            ITransformation newColorChange = new ChangeColor(copyS, new TimePeriod(t1, t2), new Color(r2, g2, b2));

            m.addTransformations(newColorChange);
          }
          if (x1 == x2 && y1 == y2 && w1 == w2 && h1 == h2 && r1 == r2 && g1 == g2 && b1 == b2) {
            ITransformation newNoChange = new NoChange(copyS, new TimePeriod(t1, t2));
            m.addTransformations(newNoChange);
          }

        } else {

          List<ITransformation> transList = m.getTransList().stream().filter(t -> t.getTransShape().getName().equals(n.getName())).collect(Collectors.toList());
          IShape transShape = transList.get(0).getTransShape();

          if (x1 != x2 || y1 != y2) {

            ITransformation newMove = new Move(transShape, new TimePeriod(t1, t2), new Point2D(x2, y2));

            m.addTransformations(newMove);
          }
          if (w1 != w2 || h1 != h2) {
            ITransformation newScale = new Scale(transShape, new TimePeriod(t1, t2), new ShapeProperty(w2, h2));
            System.out.println(newScale.toString());
            m.addTransformations(newScale);
          }
          if (r1 != r2 || g1 != g2 || b1 != b2) {

            ITransformation newColorChange = new ChangeColor(transShape, new TimePeriod(t1, t2), new Color(r2, g2, b2));

            m.addTransformations(newColorChange);
          }
          if (x1 == x2 && y1 == y2 && w1 == w2 && h1 == h2 && r1 == r2 && g1 == g2 && b1 == b2) {
            ITransformation newNoChange = new NoChange(transShape, new TimePeriod(t1, t2));
            m.addTransformations(newNoChange);
          }


        }
break;
      }

    }

    return this;
  }
}



