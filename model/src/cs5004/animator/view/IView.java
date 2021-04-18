package cs5004.animator.view;

/**
 * A interface for the "text", "visual" and "svg" views. The view interface contains all
 * functionalities for the three views and then individual views suppress or provide defaults
 * for functionalities they do not implement.
 */
public interface IView {

  /**
   * Used in the visual view for getting the panel of shapes.
   * @return SwingPanel.
   */

  SwingPanel getPanel();

  /**
   * Used in the visual view for refreshing the screen and generating animation.
   */

  void refresh();

  /**
   * Get the view type of each view.
   * @return a string indicating the view type.
   */

  String getViewType();


  void getOutPut();

  String play();
}
