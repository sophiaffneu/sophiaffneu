package cs5004.animator.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * An animation model class.
 */
public class AnimationModel implements IAnimator {
  private List<IShape> shapeList;
  private List<ITransformation> transList;
  private int x;
  private int y;
  private int width;
  private int height;

  /**
   * Construct an animation model.
   */
  public AnimationModel() {
    this.shapeList = new ArrayList<>();
    this.transList = new ArrayList<>();
  }

  @Override
  public IAnimator setBounds(int x, int y, int width, int height) {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("invalid width or height");
    }
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    return this;
  }


  @Override
  public int getX() {
    return x;
  }

  @Override
  public int getY() {
    return y;
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public void addShape(IShape shape) {
    if (shape == null) {
      throw new IllegalArgumentException("Shape can't be null.");
    }
    if (shapeList.contains(shape)) {
      throw new IllegalArgumentException("Shape already exist in list.");
    }
    shapeList.add(shape);
  }

  @Override
  public void removeShape(IShape shape) {
    if (shape == null) {
      throw new IllegalArgumentException("Shape can't be null.");
    }
    if (!shapeList.contains(shape)) {
      throw new IllegalArgumentException("The list doesn't have the shape to be removed.");
    }
    shapeList.remove(shape);
  }

  @Override
  public List<IShape> getShapeList() {
    return shapeList;
  }

  @Override
  public void addTransformations(ITransformation t) {
    if (t == null) {
      throw new IllegalArgumentException("Transformation can't be null.");
    }
    // If there is same type of transformations exits in the list, t and the existing
    // transformations can not happen at the time.
    // count is the number of existing transformations in the list has the same type as t and the
    //time periods overlap.

    for (ITransformation existT : transList) {
      if (existT.getTransShape().getName().equals(t.getTransShape().getName())
              && existT.getTransType() == t.getTransType()
              && existT.getTimePeriod().getStart() <= t.getTimePeriod().getStart()
              && existT.getTimePeriod().getEnd() >= t.getTimePeriod().getEnd()) {
        throw new IllegalArgumentException("Same type of transformation with overlapping time "
                + "period already exist.");
      }
    }
    transList.add(t);
  }

  @Override
  public void removeTransformations(ITransformation t) {
    if (t == null) {
      throw new IllegalArgumentException("Parameter can't be null.");
    }

    if (!transList.contains(t)) {
      throw new IllegalArgumentException("The list doesn't have the transformation to be removed.");
    }
    transList.remove(t);
  }

  @Override
  public List<ITransformation> getTransList() {
    return transList;
  }

  @Override
  public List<IShape> getShapeAtTick(int t, int speed) {
    int tick = 1000/speed  + t ;
    System.out.println("Animation model 127 tick= " + tick);
    List<IShape> shapeAtTick = new ArrayList<>();
    List<ITransformation> transformationsAtTick = this.getTransList().stream().filter(r -> r.getTimePeriod().getStart() * 1000/speed <= tick && r.getTimePeriod().getEnd() * 1000/speed >= tick).collect(Collectors.toList());
    System.out.println("Animation model 130 trans At Tick size = " + transformationsAtTick.size());
    for (ITransformation l : transformationsAtTick) {
      System.out.println("animation model 132  tranformation at tick list = " + l.toString() + "  "+l.getShapeProperty().getOne());
    }

    if (transformationsAtTick.size() == 0) {
      return null;
    }
    boolean moved = false;
    boolean scaled = false;
    for (ITransformation m : transformationsAtTick) {
        //get the transShape of a certain transformation.
      IShape transShape = m.getTransShape();
      //System.out.println(transShape.getName().toString());
      IShape copyTransShape = transShape.copyShape();
      // check if the shape is in list of shapeAtTick or not.
      // if a shape have multiple transformations at tick, it may be in the list already. Find the shape and add new transformation to it.
      List<IShape> transShapeList = shapeAtTick.stream().filter(k -> k.getName().equals(m.getTransShape().getName())).collect(Collectors.toList());
      IShape transShapeExist = null;
      if(transShapeList.size() != 0){transShapeExist = transShapeList.get(0);}

      double ratioOfChange = (tick - m.getTimePeriod().getStart() * (1000/speed)) /
              ((1000.0/speed) * (m.getTimePeriod().getEnd() - m.getTimePeriod().getStart()));

      if (m.getTransType() == TransType.NOCHANGE) {
        copyTransShape.setPosition(m.getPosition());
        copyTransShape.setShapeProperty(m.getShapeProperty());
        copyTransShape.setColor(m.getColor());
        shapeAtTick.add(copyTransShape);    }
      else if (m.getTransType() == TransType.MOVE) {
//m.getPosition is position before transformation, transShape.getPosition is position after transformation.
        int x = m.getPosition().getX() + (int) ((m.getToPosition().getX() - m.getPosition().getX()) * ratioOfChange);
        int y = m.getPosition().getY() + (int) ((m.getToPosition().getY() - m.getPosition().getY()) * ratioOfChange);

          copyTransShape.setPosition(new Point2D(x, y));
          copyTransShape.setShapeProperty(m.getShapeProperty());
          copyTransShape.setColor(m.getColor());
          shapeAtTick.add(copyTransShape);
          moved = true;
        }
          else  if (m.getTransType() == TransType.SCALE) {

        int shapeProperty1 = m.getShapeProperty().getOne()
                + (int) ((m.getToShapeProperty().getOne()
                - m.getShapeProperty().getOne()) * ratioOfChange);

        int shapeProperty2 = m.getShapeProperty().getTwo() + (int) ((m.getToShapeProperty().getTwo() - m.getShapeProperty().getTwo()) * ratioOfChange);

        if (transShapeList.size() == 0) {
          copyTransShape.setShapeProperty(new ShapeProperty(shapeProperty1, shapeProperty2));
          copyTransShape.setPosition(m.getPosition());
          copyTransShape.setColor(m.getColor());
          shapeAtTick.add(copyTransShape);
        } else {
          scaled = true;
          transShapeExist.setShapeProperty(new ShapeProperty(shapeProperty1, shapeProperty2)); //transShapeExist already exit in transformationAtTick list, no need to add.
          transShapeExist.setColor(m.getColor());
        }
      }

      else  if (m.getTransType() == TransType.CHANGECOLOR) {

        int R = m.getColor().getRed() + (int) ((m.getToColor().getRed() - m.getColor().getRed()) * ratioOfChange);
        int G = m.getColor().getGreen() + (int) ((m.getToColor().getGreen() - m.getColor().getGreen()) * ratioOfChange);
        int B = m.getColor().getBlue() + (int) ((m.getToColor().getBlue() - m.getColor().getBlue()) * ratioOfChange);

        if (transShapeList.size() == 0) {
          copyTransShape.setColor(new Color(R, G, B));
          copyTransShape.setShapeProperty(m.getShapeProperty());
          copyTransShape.setPosition(m.getPosition());
          shapeAtTick.add(copyTransShape);
        } else {
          transShapeExist.setColor(new Color(R, G, B)); //transShapeExist already exit in transformationAtTick list, no need to add.
          if(moved == true && scaled == false){
          transShapeExist.setShapeProperty(m.getShapeProperty());
          }
          else if(moved == false && scaled == true){
            transShapeExist.setPosition(m.getPosition());
          }
        }
      }
      moved = false;
      scaled = false;
    }
    return shapeAtTick;
  }
}
