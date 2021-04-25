package cs5004.animator.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.stream.Collectors;

import javax.swing.*;
import javax.swing.border.LineBorder;

import cs5004.animator.model.IShape;


/**
 * This is a class of visual view. It draws and plays the animation inside a window.
 */
public class NewView extends JFrame implements IView {
  private SwingPanel panel;
  private JPanel buttonPanel;
  private Timer timer;
  private int elapsedTime;
  private int speed;
  private JButton startButton, resumeButton, pauseButton, restartButton, increaseSpeedB,
          decreaseSpeedB, cycleButton;

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

    buttonPanel = new JPanel();
    buttonPanel.setBackground(Color.YELLOW);
    buttonPanel.setBounds(200, 70, 700, 45);
    this.add(buttonPanel);
    buttonPanel.add(startButton);
    buttonPanel.add(pauseButton);
    buttonPanel.add(resumeButton);
    buttonPanel.add(restartButton);
    buttonPanel.add(increaseSpeedB);
    buttonPanel.add(decreaseSpeedB);
    buttonPanel.add(cycleButton);

    this.panel = new SwingPanel(width, height);
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
    return "newview";
  }

  @Override
  public String getOutPut() {
    return null;
  }

  @Override
  public void play() {
    // interface requirement
  }

  public JButton getStartButton() {
    return startButton;
  }

  public JButton getResumeButton() {
    return resumeButton;
  }

  public JButton getPauseButton() {
    return pauseButton;
  }

  public JButton getRestartButton() {
    return restartButton;
  }

  public JButton getIncreaseSpeedB() {
    return increaseSpeedB;
  }

  public JButton getDecreaseSpeedB() {
    return decreaseSpeedB;
  }

  public JButton getCycleButton() {
    return cycleButton;
  }

}