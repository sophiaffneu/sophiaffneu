package cs5004.animator.view;

import cs5004.animator.model.AnimationModel;

public class ViewFactory {
  private IView view;

  public IView generateView(String viewType, AnimationModel model, int tempo) {
    switch (viewType) {
      case "text":
        return new TextView(model);
      case "svg":
        return new SVGView(model, tempo);
      //case "visual":
      //return new TextView(model,tempo);
      default:
        return null;
    }
  }
}
