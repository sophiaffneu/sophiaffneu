package cs5004.animator.Controller;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.IAnimator;
import cs5004.animator.view.IView;
import cs5004.animator.view.SVGView;
import cs5004.animator.view.SVGViewNoModel;
import cs5004.animator.view.TextView;
import cs5004.animator.view.TextViewNoModel;
import cs5004.animator.view.VisualView;

/**
 * This is a view generator. Base on user input of a String "viewType", the factory generates
 * a corresponding view.
 */

public class ViewFactory {

  /**
   * A factory that generate the corresponding view according to input.
   * @param viewType a String indicating view type. The options for the view name are "text",
   *                 "visual" and "svg".
   * @param model the model that the view needs to show.
   * @param tempo the speed of animation in ticks per second.
   * @return a view corresponding to view type parameter.
   */

  public IView generateView(String viewType, IAnimator model, int tempo) {
    switch (viewType) {
      case "text":
        return new TextViewNoModel(model.getShapeList(), model.getTransList());
      case "svg":
        return new SVGViewNoModel(model.getShapeList(), model.getTransList(), tempo, model.getWidth(), model.getHeight());
      case "visual":
        return new VisualView(model.getX(), model.getY(), model.getWidth(), model.getHeight(), tempo);
      default:
        return null;
    }
  }
}