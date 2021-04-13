package cs5004.animator.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;

import cs5004.animator.model.IAnimator;
import cs5004.animator.model.IShape;
import cs5004.animator.model.ITransformation;

public class VisualView extends JFrame {
  private SwingPanel panel;

  public VisualView(int x, int y, int width, int height) {
    super();
    this.setTitle("EasyAnimator");
    this.setSize(width, height);
    this.setLocation(x, y);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.panel = new SwingPanel();
    //panel.setShapeAtTick(shapeAtTick);
    //System.out.println("panel in v constructor" + shapeAtTick.size());
    this.add(this.panel);
    this.panel.setVisible(true);

    JScrollPane scrollPane = new JScrollPane(panel);
    scrollPane.setPreferredSize(new Dimension(500, 500));
    this.add(scrollPane);

    this.setVisible(true);
  }
  public SwingPanel getPanel() {
    return this.panel;
  }
  public void currentView() {
    System.out.println("printing first view" );
    repaint();
  }
}







