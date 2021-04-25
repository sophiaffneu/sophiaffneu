package cs5004.animator.view;

/**
 * A interface for the "text", "visual" and "svg" views. The view interface contains all
 * functionalities for the three views and then individual views suppress or provide defaults for
 * functionalities they do not implement.
 */
public interface IView {

  /**
   * To get the swing panel.
   *
   * @return the swing panel.
   */
  SwingPanel getPanel();

  /**
   * To refresh the screen for animation effect.
   */
  void refresh();

  /**
   * Get the view type of each view.
   *
   * @return a string indicating the view type.
   */
  String getViewType();

  /**
   * Get the output of the view.
   *
   * @return a string that shows the output
   */
  String getOutPut();

  /**
   * Run the view.
   */
  void play();

}