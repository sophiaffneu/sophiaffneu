package cs5004.animator.view;

/**
 * This is a class of visual view. It draws and plays the animation inside a window.
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;

import cs5004.animator.model.IAnimator;
import cs5004.animator.model.IShape;
import cs5004.animator.model.ITransformation;

public class VisualView extends JFrame implements IView {
  private SwingPanel panel;
  private int speed;

  /**
   * construct a visual view
   * @param x the x coordinate of the canvas;
   * @param y the y coordinate of the canvas;
   * @param width the width of the canvas;
   * @param height the height of the canvas;
   * @param speed the playing speed of the animation.
   */
  public VisualView(int x, int y, int width, int height, int speed) {
    super();
    this.setTitle("EasyAnimator");
    this.setSize(width, height);
    this.setLocation(x, y);
    this.speed = speed;
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.panel = new SwingPanel();
    panel.setPreferredSize(new Dimension(width, height));
    this.add(this.panel);
    this.panel.setVisible(true);

    JScrollPane scrollPane = new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrollPane.setPreferredSize(new Dimension(width, height));
    this.add(scrollPane);

    this.setVisible(true);
  }

  /**
   * To get the swing panel
   * @return the swing panel.
   */
  public SwingPanel getPanel() {
    return this.panel;
  }

  /**
   * To refresh the screen for animation effect.
   */
  @Override
  public void refresh() {
    repaint();
  }

  @Override
  public String getViewType() {
    return "visual";
  }

  @Override
    public void getOutPut () {
         }

  @Override
  public String play() {
    return null;
  }
}








