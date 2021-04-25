package cs5004.animator.view;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.Timer;


/**
 * This is a class of visual view. It draws and plays the animation inside a window.
 */
public class NewView extends JFrame implements IView {
  private SwingPanel panel;
  private JPanel buttonPanel;
  private Timer timer;
  private int elapsedTime;
  private int speed;
  private JButton startButton;
  private JButton resumeButton;
  private JButton pauseButton;
  private JButton restartButton;
  private JButton increaseSpeedB;
  private JButton decreaseSpeedB;
  private JButton cycleButton;
  private JButton stopCycleButton;
  private JButton saveSVGButton;
  private JButton saveTextButton;

  /**
   * Construct a visual view.
   *
   * @param x      the x coordinate of the canvas;
   * @param y      the y coordinate of the canvas;
   * @param width  the width of the canvas;
   * @param height the height of the canvas;
   * @param speed  the playing speed of the animation.
   */
  public NewView(int x, int y, int width, int height, int speed) {
    super();
    this.speed = speed;

    this.setTitle("EasyAnimator");
    this.setSize(width, height);
    this.setLocation(x, y);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.startButton = new JButton("START");
    this.resumeButton = new JButton("RESUME");
    this.restartButton = new JButton("RESTART");
    this.pauseButton = new JButton("PAUSE");
    this.increaseSpeedB = new JButton("INCREASE SPEED");
    this.decreaseSpeedB = new JButton("DECREASE SPEED");
    this.cycleButton = new JButton("CYCLE");
    this.stopCycleButton = new JButton("STOPCYCLE");
    this.saveSVGButton = new JButton("SAVESVG");
    this.saveTextButton = new JButton("SAVETEXT");

    buttonPanel = new JPanel();
    buttonPanel.setBackground(Color.YELLOW);
    buttonPanel.setBounds(0, 0, width, 45);
    this.add(buttonPanel);
    buttonPanel.add(startButton);
    buttonPanel.add(pauseButton);
    buttonPanel.add(resumeButton);
    buttonPanel.add(restartButton);
    buttonPanel.add(increaseSpeedB);
    buttonPanel.add(decreaseSpeedB);
    buttonPanel.add(cycleButton);
    buttonPanel.add(stopCycleButton);
    buttonPanel.add(saveSVGButton);
    buttonPanel.add(saveTextButton);

    this.panel = new SwingPanel(width, height);
    panel.add(buttonPanel);
    this.add(this.panel);
    this.panel.setVisible(true);

    JScrollPane scrollPane = new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrollPane.setPreferredSize(new Dimension(width, height));
    this.add(scrollPane);

    this.setVisible(true);
  }

  public SwingPanel getPanel() {
    return this.panel;
  }

  @Override
  public void refresh() {
    repaint();
  }

  @Override
  public String getViewType() {
    return "playback";
  }

  @Override
  public String getOutPut() {
    throw new UnsupportedOperationException("unsupported");
  }

  @Override
  public void play() {
    throw new UnsupportedOperationException("unsupported");
  }

  @Override
  public JButton getStartButton() {
    return startButton;
  }

  @Override
  public JButton getResumeButton() {
    return resumeButton;
  }

  @Override
  public JButton getPauseButton() {
    return pauseButton;
  }

  @Override
  public JButton getRestartButton() {
    return restartButton;
  }

  @Override
  public JButton getIncreaseSpeedB() {
    return increaseSpeedB;
  }

  @Override
  public JButton getDecreaseSpeedB() {
    return decreaseSpeedB;
  }

  @Override
  public JButton getCycleButton() {
    return cycleButton;
  }

  @Override
  public JButton getStopCycleButton() {
    return stopCycleButton;
  }

  @Override
  public JButton getSaveSVGButton() {
    return saveSVGButton;
  }

  @Override
  public JButton getSaveTextButton() {
    return saveTextButton;
  }
}