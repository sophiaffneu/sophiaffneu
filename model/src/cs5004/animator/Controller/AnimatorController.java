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
import cs5004.animator.view.IView;
import cs5004.animator.view.SVGViewNoModel;
import cs5004.animator.view.TextView;
import cs5004.animator.view.TextViewNoModel;
import cs5004.animator.view.VisualView;

public class AnimatorController implements ActionListener {

  IAnimator m;
  IView v;
  int elapsedTime = 0;
  int speed;

  public AnimatorController(IView view, IAnimator model, int tempo) {
    this.v = view ;
    this.m = model;
    this.speed = tempo;
  }

  Timer timer = new Timer(1000/speed,this);

  public void go(IView v)  {
    switch (v.getViewType()) {
      case "text":
        v.getOutPut();
        break;
      case "svg":
        v.play();
        break;
      case "visual":
        timer.start();
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    elapsedTime += 1000/speed;
    List<IShape> shapeAtTick = m.getShapeAtTick(elapsedTime, 20);
    if (shapeAtTick == null) {
        timer.stop();
      }
      v.getPanel().setShapeAtTick(shapeAtTick);
      v.refresh();
    }
  }

