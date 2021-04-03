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

  public void addShape(IShape shape) {
    shapeList.add(shape);
  }

  @Override
  public void removeShape(IShape shape) {
    shapeList.remove(shape);
  }

  public List<IShape> getShapeList() {
    return shapeList;
  }

  @Override
  public void addTransformations(ITransformation t) {
    transList.add(t);
  }

  @Override
  public void removeTransformations(IShape s) {
transList.remove(s);
  }

  @Override
  public List<ITransformation> getTransList() {
    return transList;
  }

  // @Override
//public List<IShape> getShapeAtTick(int t) {

  public String getShapesInfo(){
    String output1 = "Shapes:\n";
    for (IShape shape : shapeList) {
      output1 += shape.toString() + "\n";
    }
    return output1;
  }

  public String getTransInfo() {
    List<ITransformation> sortedList = new ArrayList<>();
    String output2 = "";
    sortedList = transList.stream().sorted((t1, t2) -> Integer.compare(t1.getTimePeriod().getStart(),
        t2.getTimePeriod().getStart())).collect(Collectors.toList());
    for(ITransformation t : sortedList) {
      output2 += t.toString() + "\n";
    }
    return output2;
  }


}
