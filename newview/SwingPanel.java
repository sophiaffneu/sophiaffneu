package cs5004.animator.view;

import cs5004.animator.model.IShape;
import cs5004.animator.model.ShapeType;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;


/**
 * This is a component inside the visual view for playing animation of all the shapes.
 */
public class SwingPanel extends JPanel {
  public List<IShape> shapeAtTick = new ArrayList<>();

  /**
   * Construct a swing panel.
   *
   * @param width  width of the canvas.
   * @param height height of the canvas.
   */
  public SwingPanel(int width, int height) {
    super(true);
    setBackground(Color.WHITE);
    setBounds(120, 70, width , width);
    setLocation(120, 70);
    setBorder(new LineBorder(Color.BLACK, 2));
  }

  /**
   * Construct a swing panel.
   */
 /* public SwingPanel() {
    super(true);
    setBackground(Color.WHITE);
    setBorder(new LineBorder(Color.BLACK, 2));
  }*/

  /**
   * Set the shape list at a certain tick.
   *
   * @param s a list of shapes generated by the model for the animation
   */
  public void setShapeAtTick(List<IShape> s) {
    this.shapeAtTick = s;
  }

  public  List<IShape> getShapeAtTick(){ return this.shapeAtTick;}

  /**
   * Paint the panel at a certain tick.
   *
   * @param g a Graphics type as the painting tool.
   */

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;

    if (shapeAtTick == null) {
      return;
    }
    for (IShape s : shapeAtTick) {
      g2.setColor(s.getColor());
      if (s.getShapeType() == ShapeType.RECTANGLE) {
        g2.drawRect(s.getPosition().getX(), s.getPosition().getY(), s.getShapeProperty().getOne(),
            s.getShapeProperty().getTwo());
        g2.fillRect(s.getPosition().getX(), s.getPosition().getY(), s.getShapeProperty().getOne(),
            s.getShapeProperty().getTwo());
      } else {
        g2.drawOval(s.getPosition().getX(), s.getPosition().getY(), s.getShapeProperty().getOne(),
            s.getShapeProperty().getTwo());
        g2.fillOval(s.getPosition().getX(), s.getPosition().getY(), s.getShapeProperty().getOne(),
            s.getShapeProperty().getTwo());
      }
    }
  }
}