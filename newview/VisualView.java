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
public class VisualView extends JFrame implements IView, ActionListener {
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
  public VisualView(int x, int y, int width, int height, int speed) {
    super();

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
    buttonPanel.setBounds(120, 70, 300, 90);
    buttonPanel.setLayout(new GridLayout(2, 3, 10, 10));
    this.add(buttonPanel);
    buttonPanel.add(startButton);
    buttonPanel.add(resumeButton);
    buttonPanel.add(restartButton);
    buttonPanel.add(increaseSpeedB);
    buttonPanel.add(decreaseSpeedB);
    buttonPanel.add(pauseButton);
    buttonPanel.add(cycleButton);

    this.panel = new SwingPanel();
    panel.setBackground(Color.WHITE);
    panel.setBounds(120, 70, 30, 30);
    panel.setLocation(120, 70);
    panel.setBorder(new LineBorder(Color.BLACK, 2));
    panel.setPreferredSize(new Dimension(width, height));
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
    return "visual";
  }

  @Override
  public String getOutPut() {
    return null;
  }

  @Override
  public void play() {
    // interface requirement
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == startButton) {

      elapsedTime += 1000 / this.speed;
      timer = new Timer(1000 / speed, this);
      timer.start();
       /* if (shapeAtTick == null) {
      timer.stop();
    }*/
      this.refresh();
    }

    else if (e.getSource() == restartButton) {

    }

    else if (e.getSource() == resumeButton) {

    }

    else if (e.getSource() == increaseSpeedB) {

    }
  //  ... keep adding more buttons


  }
}
