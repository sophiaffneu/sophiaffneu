package cs5004.animator.Controller;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.IAnimator;
import cs5004.animator.view.IView;
import cs5004.animator.view.SVGView;
import cs5004.animator.view.SVGViewNoModel;
import cs5004.animator.view.TextView;
import cs5004.animator.view.TextViewNoModel;
import cs5004.animator.view.VisualView;

public class ViewFactory {
  private IView view;

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