package cs5004.animator.view;

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

  public SwingPanel getPanel() {
    return this.panel;
  }

  @Override
  public void refresh() {
    repaint();
  }

  @Override
  public String getViewType() {
    return "VisualView";
  }

  @Override
    public void getOutPut () {
         }

  @Override
  public String play() {
    return null;
  }
}








