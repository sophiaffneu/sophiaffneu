import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * An animation model class.
 */
public class AnimationModel implements IAnimator {
  private ArrayList<IShape> shapeList;
  private HashMap<IShape, List<ITransformation>> transMap;

  /**
   * Construct an animation model.
   */
  public AnimationModel() {
    this.shapeList = new ArrayList<>();
    this.transMap = new HashMap<>();
  }

  public void addShape(IShape shape) {
    shapeList.add(shape);
  }

  @Override
  public void removeShape(IShape shape) {
    shapeList.remove(shape);
  }

  public ArrayList<IShape> getShapeList() {
    return shapeList;
  }

  @Override
  public void addTransformations(IShape s, List<ITransformation> list) {
    transMap.put(s, s.getTransformationList());
  }

  @Override
  public void removeTransformations(IShape s) {
transMap.remove(s);
  }

  @Override
  public HashMap<IShape, List<ITransformation>> getTransMap() {
    return transMap;
  }

  public String getTransInfo() {
    String output = "";
    for (ITransformation t : transList) {
      output += t.toString() + "\n";
    }
    return output;
  }
}
