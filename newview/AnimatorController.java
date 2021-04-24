package cs5004.animator.controller;

import cs5004.animator.model.IAnimator;
import cs5004.animator.model.IShape;
import cs5004.animator.view.IView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Timer;

/**
 * This class represents an animation controller.
 */
public class AnimatorController {

  IAnimator m;
  IView v;
  int elapsedTime = 0;
  int speed;

  /**
   * Construct the initial controller.
   *
   * @param view  the view
   * @param model the model
   * @param tempo the tempo
   */
  public AnimatorController(IView view, IAnimator model, int tempo) {
    this.v = view;
    this.m = model;
    this.speed = tempo;
  }


  /**
   * Run the view.
   *
   * @param v view
   */
  public void goToView(IView v) {
    if (v.getViewType().equals("text")
            || v.getViewType().equals("svg")) {
      v.play();
    } else if (v.getViewType().equals("visual")) {
      java.util.List<IShape> shapeAtTick = m.getShapeAtTick(elapsedTime, speed);
      v.getPanel().setShapeAtTick(shapeAtTick);
      v.refresh();
    }
  }
}



