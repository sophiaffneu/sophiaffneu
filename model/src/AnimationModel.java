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

  /**
   * Construct an animation model.
   */
  public AnimationModel() {
    this.shapeList = new ArrayList<>();
    this.transList = new ArrayList<>();
  }

  @Override
  public void addShape(IShape shape) {
    if (shape == null) {
      throw new IllegalArgumentException("Shape can't be null.");
    }
    if(shapeList.contains(shape)) {
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

    long count = transList.stream().filter(l -> l.getTransShape() == t.getTransShape()
            && l.getTransType() == t.getTransType()
            && l.getTimePeriod().getStart() <= t.getTimePeriod().getStart()
            && l.getTimePeriod().getEnd() >= t.getTimePeriod().getStart()).count();
    if (count != 0) {
      throw new IllegalArgumentException("Same type of transformation with overlapping time "
              + "period already exist.");
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

  // @Override
  //public List<IShape> getShapeAtTick(int t) {

  @Override
  public String getShapesInfo() {
    String output1 = "Shapes:\n";
    for (IShape shape : shapeList) {
      output1 += shape.toString() + "\n";
    }
    return output1;
  }

  @Override
  public String getTransInfo() {
    List<ITransformation> sortedList;
    String output2 = "";
    sortedList = transList.stream().sorted(Comparator.comparingInt(t ->
            t.getTimePeriod().getStart())).collect(Collectors.toList());
    for (ITransformation t : sortedList) {
      output2 += t.toString() + "\n";
    }
    return output2;
  }
}
