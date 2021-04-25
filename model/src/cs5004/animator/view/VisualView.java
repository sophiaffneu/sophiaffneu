package cs5004.animator.view;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;


/**
 * This is a class of visual view. It draws and plays the animation inside a window.
 */
public class VisualView extends JFrame implements IView {
  private SwingPanel panel;

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
    this.panel = new SwingPanel(width, height);
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
}