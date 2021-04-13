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

  Timer timer = new Timer(200, this);

  public AnimatorController(IAnimator m) {
    this.m = m;
     v = new VisualView(20, 170, 360, 360);
  }

  public void go() throws IOException {
    timer.start();
    System.out.println("controller timer started.");
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    elapsedTime += 200;
   // System.out.println(elapsedTime);
    List<IShape> shapeAtTick = m.getShapeAtTick(elapsedTime);
    System.out.println("controller translist size " + m.getTransList().size());
   System.out.println("controller get Shape Attick size " + m.getShapeAtTick(elapsedTime));
    /*for(ITransformation t : m.getTransList()) {
      System.out.println(t.toString());
    }*/
    if (1 == 1) {
      if (shapeAtTick == null) {
        timer.stop();
        System.out.println("timer stopped.");
      }
      v.getPanel().setShapeAtTick(shapeAtTick);
      v.setVisible(true);
      v.currentView();
    }
  }
}
