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
  private  int x;
  private  int y;
  private  int width;
  private  int height;

  /**
   * Construct an animation model.
   */
  public AnimationModel() {
    this.shapeList = new ArrayList<>();
    this.transList = new ArrayList<>();
  }

  @Override
  public IAnimator setBounds(int x, int y, int width, int height) {
    this.x = x;
    this.y = y;
    this.width =width;
    this.height = height;
    return this;
  }


  @Override
  public int getX(){
    return  x;
  }

  @Override
  public int getY(){
    return  y;
  }

  @Override
  public int getWidth(){
    return  width;
  }

  @Override
  public int getHeight(){
    return  height;
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

    for(ITransformation existT:transList){
      if(existT.getTransShape().getName().equals(t.getTransShape().getName())
          && existT.getTransType() == t.getTransType()
          && existT.getTimePeriod().getStart() <= t.getTimePeriod().getStart()
          && existT.getTimePeriod().getEnd() >= t.getTimePeriod().getEnd()){
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
  public List<IShape> getShapeAtTick(int t) {
    int tick = t / 200;
    System.out.println("Animation model tick= " + tick);
    List<IShape> shapeAtTick = new ArrayList<>();
    List<ITransformation> transformationsAtTick = this.getTransList().stream().filter(r -> r.getTimePeriod().getStart() <= tick && r.getTimePeriod().getEnd() >= tick).collect(Collectors.toList());
    System.out.println("Animation model transAtTick size = " + transformationsAtTick.size());
    for(ITransformation l : transformationsAtTick){
      System.out.println("tranformation at tick list = " + l.toString() + l.getShapeProperty().getOne());
    }


    if (transformationsAtTick.size() == 0) {
      return null;
    }

    for (ITransformation m : transformationsAtTick) {
      //get the transShape of a certain transformation.
      IShape transShape = m.getTransShape();
      System.out.println(transShape.getName().toString());
      IShape copyTransShape = transShape.copyShape();
      // check if the shape is in list of shapeAtTick or not.
      System.out.println("animation model shape at tick size = " + shapeAtTick.size());
      long count = shapeAtTick.stream().filter(s -> s.getName().equals(transShape.getName())).count();

      System.out.println("if count = 0, first time add shape in shapeAttick" + count);
      // if a shape have multiple transformations at tick, it may be in the list already. Find the shape and add new transformation to it.
      List<ITransformation> transShapeList = transformationsAtTick.stream().filter(k -> k.getTransShape().getName().equals(m.getTransShape().getName())).collect(Collectors.toList());
      IShape transShapeExist = transShapeList.get(0).getTransShape();
      System.out.println("transShapeexist" + transShapeExist.getName());
      System.out.println("transtype" + transShapeList.get(0).getTransType());

      if(m.getTransType() == TransType.NOCHANGE) {
        copyTransShape.setPosition(m.getPosition());
        copyTransShape.setShapeProperty(m.getShapeProperty());
        System.out.println("NoChange   " +m.getShapeProperty().getOne());
        copyTransShape.setColor(m.getColor());
        shapeAtTick.add(copyTransShape);
      }

      if (m.getTransType() == TransType.MOVE) {
//m.getPosition is position before transformation, transShape.getPosition is position after transformation.
        int x = m.getPosition().getX() + (((m.getToPosition().getX() - m.getPosition().getX())
                / m.getTimePeriod().getEnd() - m.getTimePeriod().getStart())) * (tick - m.getTimePeriod().getStart());
        int y = m.getPosition().getY() + (((m.getToPosition().getY() - m.getPosition().getY())
                / m.getTimePeriod().getEnd() - m.getTimePeriod().getStart())) * (tick - m.getTimePeriod().getStart());

        if (count == 0) {
          copyTransShape.setPosition(new Point2D(x, y));
          copyTransShape.setShapeProperty(m.getShapeProperty());
          copyTransShape.setColor(m.getColor());
          shapeAtTick.add(copyTransShape);
        } else {
          //transShapeExist already exit in transformationAtTick list, no need to add.
          transShapeExist.setPosition(new Point2D(x, y));

        }
      } else if (m.getTransType() == TransType.SCALE) {

        int shapeProperty1 = m.getShapeProperty().getOne()
                + ((m.getToShapeProperty().getOne()
                - m.getShapeProperty().getOne())
                / m.getTimePeriod().getEnd() - m.getTimePeriod().getStart()) * (tick - m.getTimePeriod().getStart());

        int shapeProperty2 = m.getShapeProperty().getTwo()
                + ((m.getToShapeProperty().getTwo()
                - m.getShapeProperty().getTwo())
                / m.getTimePeriod().getEnd() - m.getTimePeriod().getStart()) * (tick - m.getTimePeriod().getStart());
        if (count == 0) {
          copyTransShape.setShapeProperty(new ShapeProperty(shapeProperty1, shapeProperty2));
          shapeAtTick.add(copyTransShape);
        } else {
          transShapeExist.setShapeProperty(new ShapeProperty(shapeProperty1, shapeProperty2)); //transShapeExist already exit in transformationAtTick list, no need to add.
        }
      } else if (m.getTransType() == TransType.CHANGECOLOR) {

        int R = m.getColor().getRed() + (m.getToColor().getRed() - m.getColor().getRed()
                / m.getTimePeriod().getEnd() - m.getTimePeriod().getStart()) * (tick - m.getTimePeriod().getStart());
        int G = m.getColor().getGreen() + (m.getToColor().getGreen() - m.getColor().getGreen()
                / m.getTimePeriod().getEnd() - m.getTimePeriod().getStart()) * (tick - m.getTimePeriod().getStart());
        int B = m.getColor().getBlue() + (m.getToColor().getBlue() - m.getColor().getBlue()
                / m.getTimePeriod().getEnd() - m.getTimePeriod().getStart()) * (tick - m.getTimePeriod().getStart());
        if (count == 0) {
          copyTransShape.setColor(new Color(R, G, B));
          shapeAtTick.add(copyTransShape);
        } else {
          transShapeExist.setColor(new Color(R, G, B)); //transShapeExist already exit in transformationAtTick list, no need to add.
        }

      } else if (m.getTransType() == TransType.NOCHANGE) {
        if (count == 0) {
          shapeAtTick.add(copyTransShape);
        }
      }
    }
    System.out.println("Animation model Print shape At tick shapes");
    for(IShape n : shapeAtTick){
      System.out.println(n.getName().toString() +" " + n.getShapeProperty().getOne() +" "+ n.getShapeProperty().getTwo()+" " + n.getColor().toString() + "\n");
    }
    return shapeAtTick;
  }
}
