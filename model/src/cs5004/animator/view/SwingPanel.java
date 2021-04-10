package cs5004.animator.view;

import java.awt.*;
import java.util.List;

import javax.swing.*;
import javax.swing.border.LineBorder;

import cs5004.animator.model.IShape;
import cs5004.animator.model.ShapeType;

public class SwingPanel extends JPanel {
  private List<IShape> readModel;


  public SwingPanel(List<IShape> readModel,int width, int height){
    super(true);
    this.readModel = readModel;
    setBackground(Color.WHITE);
    setSize(width,height);
    setLocation(0,0);
    setBorder(new LineBorder(Color.BLACK,3));
  }

  public SwingPanel(List<IShape> readModel){
    super(true);
    this.readModel = readModel;
    setBackground(Color.WHITE);
    setBorder(new LineBorder(Color.BLACK,3));
  }

  public void paintComponent(Graphics g){
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    if(readModel == null){
      return;
    }
    for(IShape s:readModel){
      g2.setColor(s.getColor());
      if(s.getShapeType()== ShapeType.RECTANGLE) {
        g2.drawRect(s.getPosition().getX(), s.getPosition().getY(), s.getShapeProperty().getOne(), s.getShapeProperty().getTwo());
        g2.fillRect(s.getPosition().getX(), s.getPosition().getY(), s.getShapeProperty().getOne(), s.getShapeProperty().getTwo());
      }else{
        g2.drawOval(s.getPosition().getX(), s.getPosition().getY(), s.getShapeProperty().getOne(), s.getShapeProperty().getTwo());
        g2.fillOval(s.getPosition().getX(), s.getPosition().getY(), s.getShapeProperty().getOne(), s.getShapeProperty().getTwo());
      }
      }
  }
}
