package cs5004.animator.controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.*;

import cs5004.animator.model.IAnimator;
import cs5004.animator.model.IShape;
import cs5004.animator.view.IView;
import cs5004.animator.view.NewView;;

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
      case "newview":
        ((NewView) v).getStartButton().addActionListener(this);
        ((NewView) v).getResumeButton().addActionListener(this);
        ((NewView) v).getRestartButton().addActionListener(this);
        ((NewView) v).getPauseButton().addActionListener(this);
        ((NewView) v).getIncreaseSpeedB().addActionListener(this);
        ((NewView) v).getDecreaseSpeedB().addActionListener(this);
        ((NewView) v).getCycleButton().addActionListener(this);

    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == ((NewView) v).getPauseButton()) {
      if (timer != null) {
        timer.stop();
      }
    } else if (e.getSource() == ((NewView) v).getResumeButton()) {
      if (timer != null) {
        timer.start();
      }
    } else if (e.getSource() == ((NewView) v).getStartButton()) {
      if (timer == null) {
        timer = new Timer(1000 / speed, this);
        timer.start();
      }
    } else if (e.getSource() == ((NewView) v).getRestartButton()) {
      if (timer != null) {
        timer.start();
      }
      elapsedTime = 0;
      elapsedTime += 1000 / this.speed;
      List<IShape> shapeAtTick = m.getShapeAtTick(elapsedTime, speed);
      v.getPanel().setShapeAtTick(shapeAtTick);
      if (shapeAtTick == null) {
        timer.stop();
      }
      v.refresh();
    } else if (e.getSource() == timer) {
      elapsedTime += 1000 / this.speed;
      List<IShape> shapeAtTick = m.getShapeAtTick(elapsedTime, speed);
      if(e.getSource() == ((NewView) v).getIncreaseSpeedB() || e.getSource() == ((NewView) v).getDecreaseSpeedB()){
        //speed =
      elapsedTime += 1000 / this.speed;
      shapeAtTick = m.getShapeAtTick(elapsedTime, speed);}
      v.getPanel().setShapeAtTick(shapeAtTick);
      if (shapeAtTick == null) {
        timer.stop();
      }
      v.refresh();
    }

    }


  }












