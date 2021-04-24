package cs5004.animator.controller;


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
import cs5004.animator.view.TextView;
import cs5004.animator.view.VisualView;

public class AnimatorController implements ActionListener {

  IAnimator m;
  IView v;
  int elapsedTime = 0;
  int speed;
  Timer timer;

  public AnimatorController(IView view, IAnimator model, int tempo) {
    this.v = view;
    this.m = model;
    this.speed = tempo;
  }

  public void go(IView v) {
    switch (v.getViewType()) {
      case "text":
        v.getOutPut();
        break;
      case "svg":
        v.play();
        break;
      case "visual":
        v = (VisualView)v;
        ((VisualView) v).getStartButton().addActionListener(this);
        ((VisualView) v).getResumeButton().addActionListener(this);
        ((VisualView) v).getRestartButton().addActionListener(this);
        ((VisualView) v).getPauseButton().addActionListener(this);
        ((VisualView) v).getIncreaseSpeedB().addActionListener(this);
        ((VisualView) v).getDecreaseSpeedB().addActionListener(this);
        ((VisualView) v).getCycleButton().addActionListener(this);

    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() ==((VisualView) v).getStartButton() ) {
      elapsedTime += 1000 / this.speed;
      timer = new Timer(1000 / speed, this);
      timer.start();
      List<IShape> shapeAtTick = m.getShapeAtTick(elapsedTime, speed);
      if (shapeAtTick == null) {
        timer.stop();
      v.refresh();

      }
    } else if (e.getSource() == ((VisualView) v).getPauseButton()) {

    } else if (e.getSource() == ((VisualView) v).getRestartButton()) {

    } else if (e.getSource() == ((VisualView) v).getResumeButton()) {

    }
    //  ... keep adding more buttons


  }
}

