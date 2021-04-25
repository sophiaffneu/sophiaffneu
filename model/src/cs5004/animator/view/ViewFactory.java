package cs5004.animator.view;

import cs5004.animator.model.IAnimator;

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
    switch (viewType) {
      case "text":
        return new TextView(model.getShapeList(), model.getTransList());
      case "svg":
        return new SVGView(model.getShapeList(), model.getTransList(), tempo, model.getWidth(),
            model.getHeight(), model.getX(), model.getY());
      case "visual":
        return new VisualView(model.getX(), model.getY(), model.getWidth(), model.getHeight(),
            tempo);
      case "playback":
        return new NewView(model.getX(),model.getY(),model.getWidth(),model.getHeight(),tempo);
      default:
        return null;
    }
  }
}