package cs5004.animator.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.*;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.IAnimator;
import cs5004.animator.model.IShape;
import cs5004.animator.model.ITransformation;
import cs5004.animator.view.VisualView;

public class AnimatorController implements ActionListener {

  IAnimator m;
  VisualView v;
  /* textView t;
     svgView s;
   */
  int elapsedTime = 0;
  int speed;

  Timer timer = new Timer(100,this);

  public AnimatorController(IAnimator m) {
    this.m = m;
    v = new VisualView(m.getX(),m.getY(), m.getWidth(), m.getHeight());
  }

  public void go() throws IOException {
    timer.start();
    System.out.println("controller timer started.");
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    elapsedTime += 100;
    List<IShape> shapeAtTick = m.getShapeAtTick(elapsedTime, 100);
    if (shapeAtTick == null) {
        timer.stop();
        System.out.println("timer stopped.");
      }
      v.getPanel().setShapeAtTick(shapeAtTick);
      v.setVisible(true);
      v.currentView();
    }
  }

