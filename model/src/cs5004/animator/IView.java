package cs5004.animator.view;

import javax.swing.*;

/**
 * A interface for the "text", "visual", "svg" and "playback" views. The view interface contains all
 * functionalities for the four views and then individual views suppress or
 * throw UnsupportedOperationException for functionalities they do not implement.
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

  /**
   * Return the start button.
   *
   * @return the start button
   */
  JButton getStartButton();

  /**
   * Return the resume button.
   *
   * @return the resume button
   */
  JButton getResumeButton();

  /**
   * Return the pause button.
   *
   * @return the pause button
   */
  JButton getPauseButton();

  /**
   * Return the restart button.
   *
   * @return the restart button
   */
  JButton getRestartButton();

  /**
   * Return the slider of speed.
   *
   * @return the slider of speed.
   */
  JSlider getSlider();

  /**
   * Return the label.
   *
   * @return the label.
   */
  JLabel getLabel();

  /**
   * Return the cycle button.
   *
   * @return the cycle button
   */
  JButton getCycleButton();

  /**
   * Return the stop cycle button.
   *
   * @return the stop cycle button
   */
  JButton getStopCycleButton();

  /**
   * Return the save svg button.
   *
   * @return the save svg button
   */
  JButton getSaveSVGButton();

  /**
   * Return the save text button.
   *
   * @return the save text button
   */
  JButton getSaveTextButton();
}