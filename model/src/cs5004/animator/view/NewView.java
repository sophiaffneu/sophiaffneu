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
  private JButton startButton, resumeButton, pauseButton, restartButton, cycleButton,
          stopCycleButton;
  private JButton saveSVGButton;
  private JButton saveTextButton;
  private JLabel label;
  private JSlider slider;

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
    this.cycleButton = new JButton("CYCLE");
    this.stopCycleButton = new JButton("STOPCYCLE");
    this.saveSVGButton = new JButton("SAVESVG");
    this.saveTextButton = new JButton("SAVETEXT");
    this.slider = new JSlider(0, 100, speed);
    this.label = new JLabel();
    label.setHorizontalAlignment(SwingConstants.CENTER);

    slider.setBounds(x,y, width, 10);
    slider.setPaintTicks(true);
    slider.setMinorTickSpacing(5);

    slider.setPaintTrack(true);
    slider.setMajorTickSpacing(10);

    slider.setPaintLabels(true);

    buttonPanel = new JPanel();
    buttonPanel.setBackground(Color.YELLOW);
    buttonPanel.setBounds(x, y, width, 45);
    buttonPanel.setLayout(new GridLayout(2, 3));
    this.add(buttonPanel);
    buttonPanel.add(startButton);
    buttonPanel.add(pauseButton);
    buttonPanel.add(resumeButton);
    buttonPanel.add(restartButton);
    buttonPanel.add(cycleButton);
    buttonPanel.add(stopCycleButton);
    buttonPanel.add(saveSVGButton);
    buttonPanel.add(saveTextButton);
    buttonPanel.add(slider);
    buttonPanel.add(label);

    label.setText("Current Speed = " + slider.getValue());

    this.panel = new SwingPanel(width, height);
    panel.add(buttonPanel);
    panel.add(slider);
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

  public JButton getCycleButton() {
    return cycleButton;
  }

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

  public JSlider getSlider() {
    return slider;
  }

  public JLabel getLabel() {
    return label;
  }

}