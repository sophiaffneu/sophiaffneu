package cs5004.animator.view;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cs5004.animator.model.IAnimator;
import cs5004.animator.model.IShape;
import cs5004.animator.model.ITransformation;

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
    Collections.copy(copyShapeList, model.getShapeList());
    Collections.copy(copyTransList, model.getTransList());
    switch (viewType) {
      case "text":
        return new TextView(copyShapeList, copyTransList);
      case "svg":
        return new SVGView(copyShapeList,copyTransList, tempo, model.getWidth(),
            model.getHeight(), model.getX(), model.getY());
      case "visual":
        return new VisualView(model.getX(), model.getY(), model.getWidth(), model.getHeight(),
            tempo);
      default:
        return null;
    }
  }
}