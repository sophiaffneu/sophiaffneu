package cs5004.animator.view;

import cs5004.animator.model.IAnimator;
import cs5004.animator.model.IShape;
import cs5004.animator.model.ITransformation;

import java.util.ArrayList;
import java.util.List;


/**
 * This class represents a view factory. We can generate different views by it.
 */
public class ViewFactory {
  /**
   * Generate different types of views.
   *
   * @param viewType a string that represents the view type
   * @param model    an animation model
   * @param tempo    tempo
   * @return a view
   */
  public IView generateView(String viewType, IAnimator model, int tempo) {
    List<IShape> copyShapeList = new ArrayList<>();
    List<ITransformation> copyTransList = new ArrayList<>();
    for (int i = 0; i < model.getShapeList().size(); i++) {
      copyShapeList.add(model.getShapeList().get(i));
    }
    for (int i = 0; i < model.getTransList().size(); i++) {
      copyTransList.add(model.getTransList().get(i));
    }

    switch (viewType) {
      case "text":
        return new TextView(copyShapeList, copyTransList);
      case "svg":
        return new SVGView(copyShapeList, copyTransList, tempo, model.getWidth(),
            model.getHeight(), model.getX(), model.getY());
      case "visual":
        return new VisualView(model.getX(), model.getY(), model.getWidth(), model.getHeight(),
            tempo);
      case "playback":
        return new NewView(model.getX(), model.getY(), model.getWidth(), model.getHeight(), tempo);
      default:
        return null;
    }
  }
}