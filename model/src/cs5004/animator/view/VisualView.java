package cs5004.animator.view;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import cs5004.animator.model.IShape;

public class VisualView extends JFrame {
  private SwingPanel panel;
  public VisualView(int x, int y, int width, int height, List<IShape> model){
    super();
    this.setTitle("EasyAnimator");
    this.setSize(width,height);
    this.setLocation(x,y);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.panel = new SwingPanel(model);
    this.setVisible(true);
    this.add(this.panel);
    this.panel.setVisible(true);

    JScrollPane scrollPane = new JScrollPane(panel);
    scrollPane.setPreferredSize(new Dimension(500,500));
    this.add(scrollPane);
  }

  public void currentView(List<IShape> model){
    this.repaint();
  }
}
